package com.fjd.serviceprice.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 20:42
 * @desc:
 */
@Service
@Slf4j
public class ForecastPriceService {

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        log.info("出发地经度，" + depLongitude);
        log.info("出发地纬度，" + depLatitude);
        log.info("目的地经度，" + destLongitude);
        log.info("目的地纬度，" + destLatitude);

        log.info("调用地图服务，计算距离和时长");

        log.info("读取计价规则");
        log.info("根据距离、时长和计价规则，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.13);

        return ResponseResult.success(forecastPriceResponse);
    }
}
