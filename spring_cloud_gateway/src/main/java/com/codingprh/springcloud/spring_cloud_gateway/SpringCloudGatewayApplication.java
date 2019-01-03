package com.codingprh.springcloud.spring_cloud_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableZuulServer
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

}

