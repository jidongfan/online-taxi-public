package com.fjd.servicedriveruser.service;

import com.fjd.internalcommon.dto.Car;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 7:42
 * @desc:
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtModified(now);
        car.setGmtCreate(now);

        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
