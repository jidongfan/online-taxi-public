package com.fjd.servicemap.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.DirectionResponse;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 21:35
 * @desc:
 */
@Service
@Slf4j
public class DirectionService {
    /**
     * 根据出发地和目的地的经纬度 获取距离（米）和时长（分钟） 预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(100);
        directionResponse.setDuration(10);
        return ResponseResult.success(directionResponse);
    }
}
