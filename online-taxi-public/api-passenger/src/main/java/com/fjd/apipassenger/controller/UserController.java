package com.fjd.apipassenger.controller;

import com.fjd.apipassenger.service.UserService;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/8 20:46
 * @desc:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     *根据token获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        //从HttpServletRequest中获取token
        String accessToken = request.getHeader("Authorization");

        return  userService.getUserByAccessToken(accessToken);

    }

}
