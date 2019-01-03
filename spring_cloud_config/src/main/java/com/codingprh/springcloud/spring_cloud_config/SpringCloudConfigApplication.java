package com.codingprh.springcloud.spring_cloud_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
/**
 * todo:实现配置文件不需要重启服务，就完成配置文件的刷新
 * 1、gitHub配置webhook
 * 2、发送/monitor或者actuator/bus-refresh
 */
public class SpringCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigApplication.class, args);
    }

}

