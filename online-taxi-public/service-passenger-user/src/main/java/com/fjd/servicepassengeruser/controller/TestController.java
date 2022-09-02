package com.fjd.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 13:54
 */
@RestController
public class TestController {

    @GetMapping("/")
    public String test(){
        return "service-passenger-user";
    }
}
