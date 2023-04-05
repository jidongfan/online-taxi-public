package com.fjd.serviceprice.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ForecastPriceDTO;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import com.fjd.serviceprice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 20:39
 * @desc:
 */
@RestController
public class ForecastPriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();

        return priceService.forecastPrice(depLongitude, depLatitude, destLongitude, destLatitude, cityCode, vehicleType);
    }

    /**
     * 计算实际价格
     * @param distance
     * @param duration
     * @param cityCode
     * @param vehicleType
     * @return
     */
    @PostMapping("/calculate-price")
    public ResponseResult<Double> calculatePrice(@RequestParam Integer distance, @RequestParam Integer duration, @RequestParam String cityCode, @RequestParam String vehicleType){
        return priceService.calculatePrice(distance, duration, cityCode, vehicleType);
    }


}
