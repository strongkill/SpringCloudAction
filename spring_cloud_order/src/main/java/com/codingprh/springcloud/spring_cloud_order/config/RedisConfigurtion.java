package com.codingprh.springcloud.spring_cloud_order.config;

import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * redis使用redisTemplate乱码解决
 *
 * @author codingprh
 * @create 2019-01-02 2:50 PM
 */
@Configuration
public class RedisConfigurtion {
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Bean
//    public RedisTemplate<String, Object> stringSerializerRedisTemplate() {
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);
//        return redisTemplate;
//    }
}