package com.fjd.ssedriverclientweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/2 23:20
 * @desc:
 */
@RestController
public class SseController {

    public static Map<String , SseEmitter> sseEmitterMap = new HashMap<>();

    /**
     * 监听连接消息 建立连接
     * @param driverId  连接建立者
     * @return
     */
    @GetMapping("/connect/{driverId}")
    public SseEmitter connect(@PathVariable String driverId){
        System.out.println("司机ID:" + driverId);
        //乱码问题如何解决
        SseEmitter sseEmitter = new SseEmitter(0l);

        //积累消息
        sseEmitterMap.put(driverId, sseEmitter);

        return sseEmitter;
    }

    /**
     * 发送连接消息  发送消息
     * @param driverId  消息接收者
     * @param content  消息发送内容
     * @return
     */
    @GetMapping("/push")
    public String push(@RequestParam String driverId, @RequestParam String content){

        try {
            if(sseEmitterMap.containsKey(driverId)) {
                sseEmitterMap.get(driverId).send(content);
            }else{
                return "推送失败";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "给用户：" + driverId + "，发送了消息：" + content;
    }

    @GetMapping("/close/{driverId}")
    public String close(@PathVariable String driverId){
        System.out.println("关闭连接：" + driverId);
        if(sseEmitterMap.containsKey(driverId)){
            sseEmitterMap.remove(driverId);
        }
        return "close 成功";
    }
}
