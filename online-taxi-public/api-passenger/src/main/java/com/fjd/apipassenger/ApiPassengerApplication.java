package com.fjd.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 14:18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiPassengerApplication {

    public static void main(String[] args){
        SpringApplication.run(ApiPassengerApplication.class);
    }
}
