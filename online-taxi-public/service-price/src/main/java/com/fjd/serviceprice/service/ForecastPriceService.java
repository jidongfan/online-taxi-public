package com.fjd.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.dto.PriceRule;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ForecastPriceDTO;
import com.fjd.internalcommon.response.DirectionResponse;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import com.fjd.internalcommon.util.BigDecimalUtils;
import com.fjd.serviceprice.mapper.PriceRuleMapper;
import com.fjd.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 20:42
 * @desc:
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    PriceRuleMapper priceRuleMapper;


    /**
     * 根据经纬度 计算距离、时长和价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult<ForecastPriceResponse> forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType){
        log.info("出发地经度，" + depLongitude);
        log.info("出发地纬度，" + depLatitude);
        log.info("目的地经度，" + destLongitude);
        log.info("目的地纬度，" + destLatitude);

        log.info("调用地图服务，计算距离和时长");
        //组装参数
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();

        log.info("距离：" + distance + "时长：" + duration    );

        log.info("读取计价规则");
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code",cityCode);
        queryMap.put("vehicle_type",vehicleType);

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if(priceRules.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离、时长和计价规则，计算价格");

        Double price = getPrice(distance, duration, priceRule);

        // 起步价

        // 里程费

        // 时长费

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        forecastPriceResponse.setCityCode(cityCode);
        forecastPriceResponse.setVehicleType(vehicleType);
        forecastPriceResponse.setFareType(priceRule.getFareType());
        forecastPriceResponse.setFareVersion(priceRule.getFareVersion());

        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离、时长和计价规则 计算最终价格
     * @param distance  距离
     * @param duration  时长
     * @param priceRule  计价规则
     * @return
     */
    private Double getPrice(Integer distance, Integer duration, PriceRule priceRule){

        //BigDecimal
       // BigDecimal price = new BigDecimal(0);
        Double price = 0D;

        //起步价
        Double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price, startFare);

        //总里程
        //里程费 m
        Double distanceMile = BigDecimalUtils.divide(distance, 1000);

        //起步里程
        Double startMile = (double)priceRule.getStartMile();
        Double distanceSubtract = BigDecimalUtils.subtract(distanceMile, startMile);

        //最终收费的里程数 km
        Double mile = distanceSubtract < 0 ? 0 : distanceSubtract; //小于0就是0
        //计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        //里程价格
        Double mileFare = BigDecimalUtils.multiply(mile, unitPricePerMile);
        price = BigDecimalUtils.add(price, mileFare);

        //时长费及时长的分钟数
        Double timeMinute = BigDecimalUtils.divide(duration, 60);

        //计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        //时长费用
        Double timeFare = BigDecimalUtils.multiply(timeMinute, unitPricePerMinute);
        price = BigDecimalUtils.add(price, timeFare);
        
        //保险起见设置精度
        BigDecimal priceDigDecimal = BigDecimal.valueOf(price);
        priceDigDecimal = priceDigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        return priceDigDecimal.doubleValue();
    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(3);
//
//        System.out.println(getPrice(6500, 1800, priceRule));
//    }
}
