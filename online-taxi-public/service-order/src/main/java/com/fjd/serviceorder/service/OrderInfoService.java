package com.fjd.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.OrderConstants;
import com.fjd.internalcommon.dto.OrderInfo;
import com.fjd.internalcommon.dto.PriceRule;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.internalcommon.util.RedisPrefixUtils;
import com.fjd.serviceorder.mapper.OrderInfoMapper;
import com.fjd.serviceorder.remote.ServiceDriverUserClient;
import com.fjd.serviceorder.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@Service
@Slf4j
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * 下订单
     * @param orderRequest
     * @return
     */
    public ResponseResult add(OrderRequest orderRequest){

        //根据cityCode查询当前城市是否有可用司机
        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(orderRequest.getAddress());
        log.info("测试城市是否有司机结果：" + availableDriver.getData());
        if(!availableDriver.getData()){
            return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(), CommonStatusEnum.CITY_DRIVER_EMPTY.getValue());
        }

        //需要判断计价规则的版本是否为最新
        ResponseResult<Boolean> aNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        if(!(aNew.getData())){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }

        //判断下单的设备是否是黑名单
        if (isBlackDevice(orderRequest)) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }

        //判断：下单的城市和计价规则是否正常
        if(!isPriceRuleExists(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getValue());
        }

        //有正在进行的订单就不允许创建
        if(isOrderGoingon(orderRequest.getPassengerId()) > 0L){
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(), CommonStatusEnum.ORDER_GOING_ON.getValue());
        }

        OrderInfo orderInfo = new OrderInfo();
        //把原对象orderRequest copy 到目标对象orderInfo
        BeanUtils.copyProperties(orderRequest, orderInfo);

        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success("");
    }


    /**
     * 判断 下单城市的计价规则是否正常
     * @param orderRequest
     * @return
     */
    private boolean isPriceRuleExists(OrderRequest orderRequest){

        String fareType = orderRequest.getFareType();
        int index = fareType.indexOf("$");
        String cityCode = fareType.substring(0, index);
        String vehicleType = fareType.substring(index + 1);

        PriceRule priceRule = new PriceRule();
        priceRule.setCityCode(cityCode);
        priceRule.setVehicleType(vehicleType);

        ResponseResult<Boolean> booleanResponseResult = servicePriceClient.ifPriceExists(priceRule);
        return booleanResponseResult.getData();

    }


    /**
     * 判断下单的设备是否是黑名单
     * @param orderRequest
     * @return
     */
    private boolean isBlackDevice(OrderRequest orderRequest) {
        //需要判断，下单的设备是否是 黑名单设备
        String deviceCode = orderRequest.getDeviceCode();
        //生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + deviceCode;
        //设置key 看原来有没有key 不能进行如下操作，会造成死锁
        //Long increment = stringRedisTemplate.opsForValue().increment(deviceCodeKey);
        //设置过期时间
        //stringRedisTemplate.expire(deviceCodeKey, 1, TimeUnit.MINUTES);
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if(aBoolean){
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if(i >= 2){
                //当前设备超过下单次数
                return true;
            }else{
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        }else{
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.HOURS);
        }
        return false;
    }


    /**
     * 判断是否有正在进行的订单
     * @param passengerId
     * @return
     */
    public Long isOrderGoingon(Long passengerId){
        //判断有正在进行的订单不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);
        queryWrapper.and(wrapper -> wrapper.eq("order_status", OrderConstants.ORDER_START)
                .or().eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.TO_START_PAY)
                .or().eq("order_status", OrderConstants.PASSENGER_GETOFF)
        );

        //有正在进行的订单就不允许创建
        Long validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        return validOrderNumber;
    }

}
