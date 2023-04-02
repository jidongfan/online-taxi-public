package com.fjd.serviceorder.controller;

import com.fjd.internalcommon.dto.OrderInfo;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.serviceorder.mapper.OrderInfoMapper;
import com.fjd.serviceorder.service.OrderInfoService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/22 7:52
 * @desc:
 */
@RestController
public class TestController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @GetMapping("/test")
    public String test(){
        return "service-order test";
    }

    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") Long orderId){
        System.out.println("并发测试：orderId：" + orderId);
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfoService.dispatchRealTimeOrder(orderInfo);
        return "test-real-time-order success";
    }

}
