package com.fjd.apidriver.service;

import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/16 0:39
 * @desc:
 */
@Service
public class VerificationDriverService {

    public ResponseResult checkAndSendVerificationCode(String driverPhone){

        //查询 service-driver-user服务中 ，该手机号的司机存不存在

        //获取验证码

        //调用第三方，发送验证码

        //存入redis

        return ResponseResult.success("");
    }
}
