package com.fjd.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 14:18
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ApiPassengerApplication {

    public static void main(String[] args){
        SpringApplication.run(ApiPassengerApplication.class);
    }
}
