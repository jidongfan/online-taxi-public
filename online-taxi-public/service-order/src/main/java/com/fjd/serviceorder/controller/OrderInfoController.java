package com.fjd.serviceorder.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.serviceorder.service.impl.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {


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
        return orderService.add(orderRequest);
    }

}
