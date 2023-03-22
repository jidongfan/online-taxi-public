package com.fjd.serviceorder.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.serviceorder.service.impl.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/22 7:58
 * @desc:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderInfoService orderService;

    /**
     * 下订单
     * @param orderRequest
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){

        System.out.println("下单行政区域：" + orderRequest.getAddress());
        return null;
    }


    @GetMapping("/testMapper")
    public String testMapper(){

        return orderService.testMapper();
    }
}
