package com.fjd.apidriver.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/5 21:12
 * @desc:
 */
@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

    /**
     * 司机发起收款
     * @param userId
     * @param identity
     * @param content
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/push")
    public ResponseResult pushPayInfo(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content);
}
