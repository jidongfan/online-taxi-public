package com.fjd.serviceverificationcode.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/31 15:32
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        double mathRandom = (Math.random()*9 + 1) * (Math.pow(10,size-1));
        int resultInt = (int)mathRandom;
        //定义返回值
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);
        return ResponseResult.success(response);
    }

}
