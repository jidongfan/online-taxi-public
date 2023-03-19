package com.fjd.apidriver.controller;

import com.fjd.apidriver.service.VerificationDriverService;
import com.fjd.internalcommon.constant.IdentityConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/16 0:32
 * @desc:
 */
@RestController
@Slf4j
public class VerificationDriverController {

    @Autowired
    private VerificationDriverService verificationDriverService;

    /**
     * 生成验证码 存入redis
     * @param verificationCodeDTO
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String driverPhone = verificationCodeDTO.getDriverPhone();
        log.info("司机的号码：" + driverPhone);
        return verificationDriverService.checkAndSendVerificationCode(driverPhone);
    }

    /**
     * 通过验证码与手机号，验证验证码是否正确
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        return verificationDriverService.checkCode(driverPhone, IdentityConstants.DRIVER_IDENTITY, verificationCode);
    }


}
