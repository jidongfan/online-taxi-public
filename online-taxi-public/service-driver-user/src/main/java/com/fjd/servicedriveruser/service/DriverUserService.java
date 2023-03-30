package com.fjd.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.DriverCarConstants;
import com.fjd.internalcommon.dto.*;
import com.fjd.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.fjd.servicedriveruser.mapper.DriverUserMapper;
import com.fjd.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Driver;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    public ResponseResult testGetDriverUser(){

        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }

    /**
     * 司机信息的创建
     * @param driverUser
     * @return
     */
    public ResponseResult addUser(DriverUser driverUser){

        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);

        driverUserMapper.insert(driverUser);
        //初始化 司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return ResponseResult.success();
    }

    /**
     * 司机信息的修改
     * @param driverUser
     * @return
     */
    public ResponseResult updateDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success();
    }


    /**
     * 通过手机号判断司机是否存在
     * @param driverPhone
     * @return
     */
    public ResponseResult<DriverUser> getDriverUserByPhone(String driverPhone){
        HashMap<String, Object> map = new HashMap<>();
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);

        if(driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }

        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;
    public ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId){

        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", carId);
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);

        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(queryWrapper);
        Long driverId = driverCarBindingRelationship.getDriverId();

        QueryWrapper<DriverUserWorkStatus> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("driver_id", driverId);
        queryWrapper1.eq("work_status", DriverCarConstants.DRIVER_WORK_STATUS_START);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(queryWrapper1);

        if(null == driverUserWorkStatus){
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(), CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getValue());
        }else{

            QueryWrapper<DriverUser> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("id", driverId);
            DriverUser driverUser = driverUserMapper.selectOne(queryWrapper2);

            OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
            orderDriverResponse.setCarId(carId);
            orderDriverResponse.setDriverId(driverId);
            orderDriverResponse.setDriverPhone(driverUser.getDriverPhone());

            return ResponseResult.success(orderDriverResponse);
        }
    }

}