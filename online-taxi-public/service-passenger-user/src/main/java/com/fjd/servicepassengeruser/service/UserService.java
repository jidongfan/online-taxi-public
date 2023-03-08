package com.fjd.servicepassengeruser.service;

import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.dto.PassengerUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 14:44
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    /**
     * 根据手机号插入用户信息
     * @param passengerPhone
     * @return
     */
    public ResponseResult loginOrRegister(String passengerPhone){
        //根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        //判断用户信息是否存在
        if(passengerUsers.size() == 0){
            //如果不存在，插入用户信息
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerName("张三"); //用户名是由app自己生产的，或者用户自己设置
            passengerUser.setPassengerGender((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);
        }

        //如果不存在，插入用户信息

        return ResponseResult.success();
    }

    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */
    public ResponseResult<PassengerUser> getUserByPhone(String passengerPhone){
        //根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        if(passengerUsers.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXIST.getCode(), CommonStatusEnum.USER_NOT_EXIST.getValue());
        }else{
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }
}
