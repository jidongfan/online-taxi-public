package com.fjd.serviceprice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.fjd.serviceprice.remote")
@MapperScan("com.fjd.serviceprice.mapper")
public class ServicePriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePriceApplication.class, args);
    }

}
