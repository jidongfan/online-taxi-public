package com.fjd.serviceorder.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/27 11:12
 * @desc:
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @GetMapping("/city-driver/is-available-driver")
    public ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);
}
