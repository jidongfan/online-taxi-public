package com.fjd.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/2 20:45
 * @desc:
 */
@Component
public class RedisConfig {

    private String protocol = "redis://";

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    public RedissonClient redisClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(protocol + redisHost + ":" + redisPort).setDatabase(0);
        return Redisson.create(config);
    }
}
