package com.fjd.apipassenger.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ForecastPriceDTO;
import com.fjd.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 9:04
 * @desc:
 */
@FeignClient("service-price")
public interface ServicePriceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);

}
