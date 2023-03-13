package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/13 22:37
 * @desc:
 */
@RestController
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){

        ResponseResult responseResult = driverUserService.addUser(driverUser);
        return responseResult;
    }
}
