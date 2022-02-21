package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <h1>Eureka服务</h1>
 *
 * <p>
 * createDate 2022/02/19 17:14:57
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@SpringBootApplication
// 注册Eureka服务端
// 查看服务详情，访问 http://localhost:7002/
// 必须先启动服务端，才能启动客户端
@EnableEurekaServer
@Slf4j
public class P03M05EurekaServer7002App {

    public static void main(String[] args) {
        SpringApplication.run(P03M05EurekaServer7002App.class, args);
        log.info("P03M05EurekaServer7002App");
    }

}
