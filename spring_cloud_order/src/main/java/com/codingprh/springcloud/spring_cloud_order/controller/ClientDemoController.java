package com.codingprh.springcloud.spring_cloud_order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 * 调用product服务
 *
 * @author codingprh
 * @create 2018-12-27 10:41 AM
 */
@RestController
@RequestMapping("/oder")
public class ClientDemoController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/msg")
    public String getProdcutMsg() {
        //服务进程通信：方式一：直接new RestTemplate()的方式
//        RestTemplate restTemplate = new RestTemplate();
//        String respone = restTemplate.getForObject("http://localhost:8081/productDemo/msg", String.class);
        //服务进程通信：方式2：使用loadBalanceClient，来获取服务名
//        ServiceInstance serviceInstance = loadBalancerClient.choose("product");
//        String url = String.format("http://%s:%s/productDemo/msg", serviceInstance.getHost(), serviceInstance.getPort());
//        String respone = restTemplate.getForObject(url, String.class);
        //todo:服务进程通信：方式三：简化方式2
        //String respone = restTemplate.getForObject("http://PRODUCT/productDemo/msg", String.class);
        return null;
    }
}