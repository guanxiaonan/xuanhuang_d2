package com.dmx.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Description:
 * @Date: Create at 17:18, 2017/12/18
 * @Author: Matthew
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
public class OneApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneApplication.class, args);
    }
}
