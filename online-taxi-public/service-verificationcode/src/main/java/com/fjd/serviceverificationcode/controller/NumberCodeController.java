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
        System.out.println("size:"+size);
        //生成验证码
        // 获取随机数
        //Math.random()获取[0.0-1)之间的一个数
        //把每个随机数转换成字符有点消耗内存，所以直接通过右移的方式转
        double mathRandom = (Math.random()*9 + 1) * (Math.pow(10,size-1));
        System.out.println(mathRandom);
        int resultInt = (int)mathRandom;
        System.out.println("generator src code:" + resultInt);
        //定义返回值
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);
        return ResponseResult.success(response);
    }

}
