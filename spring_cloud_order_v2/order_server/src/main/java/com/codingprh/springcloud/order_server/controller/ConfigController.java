package com.codingprh.springcloud.order_server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class ConfigController {
    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String print() {
        log.info("env=" + env);
        return env;
    }
}