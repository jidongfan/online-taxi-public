package com.fjd.apidriver.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/16 7:59
 * @desc:
 */
@FeignClient("service-verificationcode")
public interface ServviceVerificationcodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> getVerificationCode(@PathVariable("size") int size);

}
