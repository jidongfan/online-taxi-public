package com.fjd.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/6 22:25
 * @desc:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
        //拦截的路径
                .addPathPatterns("/**")
        //不拦截的路径
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check");
    }
}
