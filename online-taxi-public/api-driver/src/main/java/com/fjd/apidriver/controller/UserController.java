package com.fjd.apidriver.controller;

import com.fjd.apidriver.service.UserService;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.DriverUserWorkStatus;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.dto.TokenResult;
import com.fjd.internalcommon.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 查询司机车辆绑定关系的信息
     * @param request
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult getDriverCarBindingRelationship(HttpServletRequest request){
        //从token中解析司机手机号
        String authorization = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.checkToken(authorization);
        String driverPhone = tokenResult.getPhone();
        return userService.getDriverCarBindingRelationship(driverPhone);
    }



}
