package com.fjd.serviceprice.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ForecastPriceDTO;
import com.fjd.internalcommon.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/11 14:53
 * @desc:
 */
@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.GET, value = "/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);

}
