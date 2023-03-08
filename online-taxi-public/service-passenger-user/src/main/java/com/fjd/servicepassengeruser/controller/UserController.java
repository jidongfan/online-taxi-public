package com.fjd.servicepassengeruser.controller;

import com.fjd.internalcommon.dto.PassengerUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import com.fjd.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 14:18
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据手机号插入用户
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号："+passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }


    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */
    @GetMapping("/user/{phone}")
    public ResponseResult<PassengerUser> getUser(@PathVariable("phone") String passengerPhone){

        System.out.println("user-passenger-service：" + passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }
}
