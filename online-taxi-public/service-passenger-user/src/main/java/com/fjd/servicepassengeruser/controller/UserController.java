package com.fjd.servicepassengeruser.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import com.fjd.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 14:18
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号："+passengerPhone);
        return ResponseResult.success(passengerPhone);
    }
}
