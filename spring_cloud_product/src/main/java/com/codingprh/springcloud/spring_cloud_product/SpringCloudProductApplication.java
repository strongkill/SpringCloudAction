package com.codingprh.springcloud.spring_cloud_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProductApplication.class, args);
    }

}

