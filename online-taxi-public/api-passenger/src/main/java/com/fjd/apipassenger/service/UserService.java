package com.fjd.apipassenger.service;

import com.fjd.apipassenger.remote.ServiceOrderClient;
import com.fjd.apipassenger.remote.ServicePassengerUserClient;
import com.fjd.internalcommon.dto.PassengerUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.dto.TokenResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import com.fjd.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    public ResponseResult getUserByAccessToken(String accessToken){

        log.info("accessToken:" + accessToken);

        //解析accessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("乘客手机号Phone: " + phone);

        //根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }

    /**
     * 乘客下订单
     * @param orderRequest
     * @return
     */
    public ResponseResult add(OrderRequest orderRequest){
        return serviceOrderClient.add(orderRequest);
    }
}
