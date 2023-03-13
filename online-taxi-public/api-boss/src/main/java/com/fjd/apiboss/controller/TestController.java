package com.fjd.apiboss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/13 23:17
 * @desc:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "api-boss";
    }
}
