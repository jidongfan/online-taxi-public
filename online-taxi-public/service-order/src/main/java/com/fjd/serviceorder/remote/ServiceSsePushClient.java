package com.fjd.serviceorder.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/3 11:39
 * @desc:
 */
@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

    /**
     * 用户发送消息
     * @param userId
     * @param identity
     * @param content
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/push")
    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content);

}
