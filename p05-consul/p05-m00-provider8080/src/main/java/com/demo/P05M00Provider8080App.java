package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h1>提供者</h1>
 *
 * <p>
 * createDate 2022/02/19 17:14:57
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@SpringBootApplication
// consul服务发现注解
// https://www.consul.io/downloads 下载consul
// `consul.exe --version` 查看版本信息
// `consul.exe agent -dev` 开发者模式启动
// http://localhost:8500/ 管理页面
@EnableDiscoveryClient
public class P05M00Provider8080App {

    public static void main(String[] args) {
        SpringApplication.run(P05M00Provider8080App.class, args);
    }

}
