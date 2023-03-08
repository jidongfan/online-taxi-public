package com.fjd.apipassenger.service;

import com.fjd.internalcommon.dto.PassengerUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.dto.TokenResult;
import com.fjd.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/8 20:50
 * @desc:
 */
@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){

        log.info("accessToken:" + accessToken);

        //解析accessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("乘客手机号Phone: " + phone);

        //根据手机号查询用户信息

        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");

        return ResponseResult.success(passengerUser);
    }
}
