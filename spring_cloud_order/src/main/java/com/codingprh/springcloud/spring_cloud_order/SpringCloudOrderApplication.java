package com.codingprh.springcloud.spring_cloud_order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.codingprh.springcloud.spring_cloud_product.client")
@SpringCloudApplication
public class SpringCloudOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOrderApplication.class, args);
    }

}

