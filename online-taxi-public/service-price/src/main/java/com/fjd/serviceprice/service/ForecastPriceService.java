package com.fjd.serviceprice.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ForecastPriceDTO;
import com.fjd.internalcommon.response.DirectionResponse;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import com.fjd.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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


    /**
     * 根据经纬度 计算距离、时长和价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
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
        log.info("根据距离、时长和计价规则，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.13);

        return ResponseResult.success(forecastPriceResponse);
    }
}
