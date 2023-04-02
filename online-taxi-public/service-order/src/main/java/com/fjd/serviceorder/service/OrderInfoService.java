package com.fjd.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.OrderConstants;
import com.fjd.internalcommon.dto.OrderDriverResponse;
import com.fjd.internalcommon.dto.OrderInfo;
import com.fjd.internalcommon.dto.PriceRule;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.internalcommon.request.PriceRuleIsNewRequest;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.internalcommon.util.RedisPrefixUtils;
import com.fjd.serviceorder.mapper.OrderInfoMapper;
import com.fjd.serviceorder.remote.ServiceDriverUserClient;
import com.fjd.serviceorder.remote.ServiceMapClient;
import com.fjd.serviceorder.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private ServiceMapClient serviceMapClient;

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
        PriceRuleIsNewRequest priceRuleIsNewRequest = new PriceRuleIsNewRequest();
        priceRuleIsNewRequest.setFareType(orderRequest.getFareType());
        priceRuleIsNewRequest.setFareVersion(orderRequest.getFareVersion());
        ResponseResult<Boolean> aNew = servicePriceClient.isNew(priceRuleIsNewRequest);
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
        if(isPassengerOrderGoingon(orderRequest.getPassengerId()) > 0L){
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

        //派单
        dispatchRealTimeOrder(orderInfo);
        return ResponseResult.success("");
    }


    /**
     * 实时订单派单逻辑
     * @param orderInfo
     */
    public synchronized void dispatchRealTimeOrder(OrderInfo orderInfo){

        //2km内搜索
        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        String center = depLatitude + "," + depLongitude; // 注意：纬度在前经度在后

        ArrayList<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);

        ResponseResult<List<TerminalResponse>> listResponseResult = null;
        //goto是为了测试，实际上需要用变量进行控制
        redius:
        for (int i = 0; i < radiusList.size(); i++) {
            Integer radius = radiusList.get(i);
            listResponseResult = serviceMapClient.terminalAroundSearch(center, radiusList.get(i));

            log.info("在半径为" + radius + "的范围内，寻找车辆" + JSONArray.fromObject(listResponseResult.getData()).toString());
          /*  public long getLong(String key) {
                this.verifyIsNull();
                Object o = this.get(key);
                if (o != null) {
                    return o instanceof Number ? ((Number)o).longValue() : (long)this.getDouble(key);
                } else {
                    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
                }
            }
            JSONObject jsonArray = JSONObject.fromObject(listResponseResult.getData());
            jsonArray.getLong("desc"); //可能会有精度丢失
            //正确使用方式
            long des = Long.parseLong("des");*/
            JSONArray result = JSONArray.fromObject(listResponseResult.getData());
            for (int j = 0; j < result.size(); j++) {
                JSONObject jsonObject = result.getJSONObject(i);
                String carIdString = jsonObject.getString("carId");
                Long carId = Long.parseLong(carIdString);
                String tid = jsonObject.getString("tid");

                String longitude = jsonObject.getString("longitude");
                String latitude = jsonObject.getString("latitude");

                //查询是否有对应的可派单司机
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if(availableDriver.getCode() == CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()){
                    log.info("没有车辆ID： " + carId + ",对于的司机");
                    continue redius;
                }else{
                    log.info("车辆ID: " + carId + "找到了正在出车的司机");
                    OrderDriverResponse orderDriverResponse = availableDriver.getData();
                    Long driverId = orderDriverResponse.getDriverId();
                    String driverPhone = orderDriverResponse.getDriverPhone();
                    String licenseId = orderDriverResponse.getLicenseId();
                    String vehicleNo = orderDriverResponse.getVehicleNo();

                    //判断司机是否有正在进行的订单 有正在进行的订单就不允许创建
                    if(isDriverOrderGoingon(driverId) > 0L){
                        continue ;
                    }

                    //订单直接匹配司机
                    //查询当前车辆信息
                    QueryWrapper<Object> carQueryWrapper = new QueryWrapper<>();
                    carQueryWrapper.eq("id", carId);

                    //查询当前司机信息
                    orderInfo.setDriverId(driverId);
                    orderInfo.setDriverPhone(driverPhone);
                    orderInfo.setCarId(carId);

                    //从地图中来
                    orderInfo.setReceiveOrderCarLatitude(longitude + "");
                    orderInfo.setReceiveOrderCarLongitude(latitude + "");


                    orderInfo.setReceiveOrderTime(LocalDateTime.now());
                    orderInfo.setLicenseId(licenseId);
                    orderInfo.setVehicleNo(vehicleNo);
                    orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);

                    orderInfoMapper.updateById(orderInfo);

                    //退出，不在进行，司机的查找
                    break redius;
                }

            }

            //获得终端

            //解析终端

            //根据解析出来的终端，查询车辆信息

            //找到符合的车辆，进行派单

            //如果派单成功，则推出循环
        }
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
     * 判断乘客是否有正在进行的订单
     * @param passengerId
     * @return
     */
    public Long isPassengerOrderGoingon(Long passengerId){
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

    /**
     * 判断司机是否有正在进行的订单
     * @param
     * @return
     */
    public Long isDriverOrderGoingon(Long driverId){
        //判断有正在进行的订单不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverId);
        queryWrapper.and(wrapper -> wrapper.eq("order_status", OrderConstants.ORDER_START)
                .or().eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
        );

        //有正在进行的订单就不允许创建
        Long validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        log.info("司机Id: " + driverId + ",正在进行的订单数量：" + validOrderNumber);
        return validOrderNumber;
    }

}
