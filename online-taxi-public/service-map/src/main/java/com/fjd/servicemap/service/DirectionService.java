package com.fjd.servicemap.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.DirectionResponse;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import com.fjd.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MapDirectionClient mapDirectionClient;


    /**
     * 根据出发地和目的地的经纬度 获取距离（米）和时长（分钟） 预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        //调用第三方地图接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);

        return ResponseResult.success(direction);
    }
}
