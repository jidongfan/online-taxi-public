package com.fjd.servicessepush.controller;

import com.fjd.internalcommon.util.SsePrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/2 23:20
 * @desc:
 */
@RestController
@Slf4j
public class SseController{

    public static Map<String , SseEmitter> sseEmitterMap = new HashMap<>();


    /**
     * 监听连接消息 建立连接
     * @param userId  连接建立者
     * @param identity  身份验证
     * @return
     */
    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam Long userId, @RequestParam String identity){
        log.info("用户ID:" + userId + "，身份类型" + identity);
        //乱码问题如何解决
        //这里SseEmitter的timeout设置为0，表示不超时. 如果不设置为0，那么如果SseEmitter在指定的时间（AsyncSupportConfigurer设置的timeout,默认为30秒)未完成会抛出异常
        SseEmitter sseEmitter = new SseEmitter(0l);

        String sseMapKey = SsePrefixUtils.generatorSseKey(userId, identity); // $ 分开是为了避免歧义
        //积累消息
        sseEmitterMap.put(sseMapKey, sseEmitter);

        return sseEmitter;
    }

    /**
     * 发送连接消息  发送消息
     * @param userId  用户
     * @param identity 身份标识 区分司机和乘客
     * @param content 发送消息内容
     * @return
     */
    @GetMapping("/push")
    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content){
        String sseMapKey = SsePrefixUtils.generatorSseKey(userId, identity); // $ 分开是为了避免歧义

        try {
            if(sseEmitterMap.containsKey(sseMapKey)) {
                sseEmitterMap.get(sseMapKey).send(content);
            }else{
                return "推送失败";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "给用户：" + userId + "，发送了消息：" + content;
    }

    /**
     * 关闭连接
     * @param userId  用户id
     * @param identity  身份标识
     * @return
     */
    @GetMapping("/close")
    public String close(@RequestParam Long userId, @RequestParam String identity){

        String sseMapKey = SsePrefixUtils.generatorSseKey(userId, identity); // $ 分开是为了避免歧义
        System.out.println("关闭连接：" + sseMapKey);
        if(sseEmitterMap.containsKey(sseMapKey)){
            sseEmitterMap.remove(sseMapKey);
        }
        return "close 成功";
    }
}
