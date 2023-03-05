package com.fjd.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.*;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 14:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.fjd.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args){
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}
