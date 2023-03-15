package com.fjd.apiboss.controller;

import com.fjd.apiboss.service.CarService;
import com.fjd.apiboss.service.DriverUserService;
import com.fjd.internalcommon.dto.Car;
import com.fjd.internalcommon.dto.DriverCarBindingRelationship;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/14 7:31
 * @desc:
 */
@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private CarService carService;

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }

    @PostMapping("/car")
    public ResponseResult car(@RequestBody Car car){
        return carService.addCar(car);
    }
}
