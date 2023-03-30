package com.fjd.apipassenger.controller;

import com.fjd.apipassenger.service.UserService;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/21 22:28
 * @desc:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;

    /**
     * 乘客下订单
     * @param orderRequest
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);

        return userService.add(orderRequest);
    }

}
