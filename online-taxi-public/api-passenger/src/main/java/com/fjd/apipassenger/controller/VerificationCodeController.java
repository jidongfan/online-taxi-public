package com.fjd.apipassenger.controller;

import com.fjd.apipassenger.service.VerificationCodeService;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 15:08
*/

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号参数：" + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        System.out.println("手机号："+passengerPhone+"，验证码："+verificationCode);

        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }


}
