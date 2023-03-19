package com.fjd.apipassenger.controller;

import com.fjd.apipassenger.service.VerificationCodeService;
import com.fjd.internalcommon.constant.IdentityConstants;
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

    /**
     * 通过手机号 获取自动生成验证码 存入redis
     * @param verificationCodeDTO
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return verificationCodeService.generatorCode(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
    }

    /**
     * 通过验证码与手机号，验证验证码 生成token
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        return verificationCodeService.checkCode(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, verificationCode);
    }


}
