package com.fjd.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 0:16
 * @desc:
 */
@RestController
public class TestController {

    /**
     * 需要授权的接口
     * @return
     */
    @GetMapping("/auth")
    public String testAuth(){
        return "auth";
    }

    /**
     * 不需要授权的接口
     * @return
     */
    @GetMapping("/noAuth")
    public String testNoAuth(){
        return "noAuth";
    }
}
