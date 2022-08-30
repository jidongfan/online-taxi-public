package com.fjd.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 21:28
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-verificationcode";
    }
}
