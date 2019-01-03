package com.codingprh.springcloud.spring_cloud_order.service.impl;

import com.codingprh.springcloud.spring_cloud_order.SpringCloudOrderApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author codingprh
 * @create 2018-12-31 3:06 PM
 */
public class OrderServiceImplTest extends SpringCloudOrderApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void create() {

//        stringRedisTemplate.opsForValue().set("age", "22");
//        stringRedisTemplate.expire("age", 1, TimeUnit.MINUTES);
    }

    @Test
    public void redisMap2ProductInfoOutput() {
//        OrderServiceImpl orderService1=new OrderServiceImpl();
//        System.out.println(orderService1.redisMap2ProductInfoOutput("1423113435324"));
//        stringRedisTemplate.opsForValue().set("name_zh22","潘润欢");
        stringRedisTemplate.opsForHash().put("map", "key1", "潘润欢");
    }
}