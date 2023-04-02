package com.fjd.apipassenger.controller;

import com.fjd.apipassenger.remote.ServiceOrderClient;
import com.fjd.internalcommon.dto.OrderInfo;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 14:33
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }

    /**
     * 需要有token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 无Token也能正常返回
     * @return
     */
    @GetMapping("/noAuthTest")
    public ResponseResult noAuThTest(){
        return ResponseResult.success("noAuth test");
    }

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * 测试派单逻辑
     * @param orderId
     * @return
     */
    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") Long orderId){
        System.out.println("并发测试：orderId：" + orderId);
        serviceOrderClient.dispatchRealTimeOrder(orderId);
        return "test-real-time-order success";
    }
}
