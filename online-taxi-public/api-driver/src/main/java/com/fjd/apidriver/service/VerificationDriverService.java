package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceDriverUserClient;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.DriverCarConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.DriverUserExistsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/16 0:39
 * @desc:
 */
@Service
@Slf4j
public class VerificationDriverService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult checkAndSendVerificationCode(String driverPhone){

        //查询 service-driver-user服务中 ，该手机号的司机存不存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        Integer ifExists = data.getIfExists();
        if(DriverCarConstants.DRIVER_NOT_EXITS.equals(ifExists)){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info(driverPhone + "的司机不存在");


        //获取验证码

        //调用第三方，发送验证码

        //存入redis

        return ResponseResult.success("");
    }
}
