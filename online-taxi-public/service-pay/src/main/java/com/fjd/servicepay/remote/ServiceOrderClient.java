package com.fjd.servicepay.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/7 11:57
 * @desc:
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/pay")
    public ResponseResult pay(@RequestBody OrderRequest orderRequest);
}
