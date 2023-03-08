package com.fjd.servicepassengeruser.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import com.fjd.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 根据手机号插入用户
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return userService.loginOrRegister(passengerPhone);
    }


    /**
     * 根据手机号查询用户信息
     * @param verificationCodeDTO
     * @return
     */
    @GetMapping("/user")
    public ResponseResult getUser(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return userService.getUserByPhone(passengerPhone);
    }
}
