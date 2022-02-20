package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>TODO ApplicationContextConfig Title</h1>
 *
 * <p>
 * TODO ApplicationContextConfig Description
 * </p>
 *
 * <p>
 * createDate 2022/02/20 11:37:40
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}