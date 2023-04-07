package com.fjd.servicepay.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/6 21:45
 * @desc:
 */
@Component
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfig {

    private String appId; //应用id

    private String appPrivateKey; //应用私钥

    private String publicKey; //公钥

    private String notifyUrl; //

    @PostConstruct
    public void init(){

        Config config = new Config();
        //基础配置
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";

        //业务配置
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.publicKey;
        config.notifyUrl = this.notifyUrl;

        Factory.setOptions(config);
        System.out.println("支付宝配置初始化完成");

    }

}
