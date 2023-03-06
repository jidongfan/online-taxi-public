package com.fjd.apipassenger.controller;

import com.fjd.internalcommon.dto.ResponseResult;
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

    /**
     * 需要有token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 无Token也能正常返回
     * @return
     */
    @GetMapping("/noAuthTest")
    public ResponseResult noAuThTest(){
        return ResponseResult.success("noAuth test");
    }
}
