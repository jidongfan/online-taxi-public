package com.fjd.servicedriveruser.service;

import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 19:02
 * @desc:
 */
@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult testGetDriverUser(){

        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }

    public ResponseResult addUser(DriverUser driverUser){

        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);

        driverUserMapper.insert(driverUser);

        return ResponseResult.success();
    }

}
