package com.codingprh.springcloud.spring_cloud_gateway.exception;

/**
 * 描述:
 * 限流异常
 *
 * @author codingprh
 * @create 2019-01-03 10:25 AM
 */
public class RateLimitException extends RuntimeException {
    public RateLimitException() {
        super("限流成功");
    }
}