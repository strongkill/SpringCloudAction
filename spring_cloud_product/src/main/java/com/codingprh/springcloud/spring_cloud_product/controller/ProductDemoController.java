package com.codingprh.springcloud.spring_cloud_product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 服务通信：demo
 *
 * @author codingprh
 * @create 2018-12-27 10:35 AM
 */
@RestController
@RequestMapping("/productDemo")
public class ProductDemoController {
    @GetMapping("/msg")
    public String getMsg() {
        return "hello world";
    }
}