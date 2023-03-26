package com.fjd.serviceorder.controller;

import com.fjd.internalcommon.constant.HeaderParamConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.serviceorder.service.impl.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@RestController
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
    public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest httpServletRequest){

        //获取请求设备唯一码  测试通过header获取deviceCode参数
//        String deviceCode = httpServletRequest.getHeader(HeaderParamConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);


        return orderService.add(orderRequest);
    }

}
