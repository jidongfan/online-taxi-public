package com.fjd.apidriver.controller;

import com.fjd.apidriver.service.UserService;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.DriverUserWorkStatus;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 0:24
 * @desc:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
       return userService.updateUser(driverUser);
    }

    /**
     * 修改司机工作状态
     * @param driverUserWorkStatus
     * @return
     */
    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return userService.changeWorkStatus(driverUserWorkStatus);
    }



}
