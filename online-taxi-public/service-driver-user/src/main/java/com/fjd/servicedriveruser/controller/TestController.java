package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.service.impl.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 18:46
 * @desc:
 */
@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public String test(){
        return "service-driver-user";
    }

    @GetMapping("/test-db")
    public ResponseResult testDb(){
        return driverUserService.testGetDriverUser();
    }

}
