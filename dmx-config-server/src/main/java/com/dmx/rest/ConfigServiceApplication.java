package com.dmx.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description: config server 从 git 获取配置信息,供client获取
 * @Date: Create at 18:49, 2017/12/7
 * @Author: Matthew
 */
@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServiceApplication.class).web(true).run(args);
    }

}
