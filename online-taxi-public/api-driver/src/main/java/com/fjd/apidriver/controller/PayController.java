package com.fjd.apidriver.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/5 21:01
 * @desc:
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    /**
     * 司机发起收款
     * @param orderId
     * @param price
     * @return
     */
    @PostMapping("/push-pay-info")
    public ResponseResult pushPayInfo(@RequestParam Long orderId, @RequestParam Double price, @RequestParam Long passengerId){

        return null;
    }

}
