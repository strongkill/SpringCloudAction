package com.codingprh.springcloud.spring_cloud_order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 配置中心controller
 *
 * @author codingprh
 * @create 2018-12-28 2:30 PM
 */
@RestController
@RequestMapping("/env")
@Slf4j
public class ConfigController {
    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String print() {
        log.info("env=" + env);
        return env;
    }
}