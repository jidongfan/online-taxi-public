package com.fjd.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 14:33
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){

        return "test api passenger";
    }
}
