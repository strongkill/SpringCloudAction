package com.codingprh.springcloud.spring_cloud_order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 描述:
 * 熔断器控制器
 *
 * @author codingprh
 * @create 2019-01-03 11:39 AM
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    @GetMapping("/getProductList")
    @HystrixCommand
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8081/product/listForOrder", Arrays.asList("164103465734242707"), String.class);
    }

    private String defaultFallback() {
        return "当前服务器过于拥挤，请稍后再试试~~";

    }

}