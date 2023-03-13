package com.fjd.apiboss.controller;

import com.fjd.apiboss.service.DriverUserService;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

        return driverUserService.addDriverUser(driverUser);
    }
}
