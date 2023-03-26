package com.fjd.apipassenger.service;

import com.fjd.apipassenger.remote.ServicePriceClient;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ForecastPriceDTO;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 8:10
 * @desc:
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    /**
     * 根据出发地和目的地的经纬度 预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType){
        log.info("出发地经度，" + depLongitude);
        log.info("出发地纬度，" + depLatitude);
        log.info("目的地经度，" + destLongitude);
        log.info("目的地纬度，" + destLatitude);

        log.info("调用计价服务，计算价格");
        //为什么要解析出来 然后又封装起来？如果需要加其他而外参数，就不需要加其他的
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);
        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecastPriceDTO);

        ForecastPriceResponse data = forecast.getData();
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(data.getPrice());
        forecastPriceResponse.setFareType(data.getFareType());
        forecastPriceResponse.setCityCode(data.getCityCode());
        forecastPriceResponse.setVehicleType(data.getVehicleType());
        forecastPriceResponse.setFareVersion(data.getFareVersion());

        return ResponseResult.success(forecastPriceResponse);
    }
}
