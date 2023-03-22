package com.fjd.serviceorder.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/22 7:52
 * @desc:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){

        return "service-order test";
    }
}
