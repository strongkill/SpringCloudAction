package com.codingprh.springcloud.spring_cloud_product.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 描述:
 * json转对象
 * 对象转json
 * json集合转对象集合
 *
 * @author codingprh
 * @create 2018-12-26 4:28 PM
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("【对象转json】错误, string={}", object);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     *
     * @param json
     * @param classType
     * @return
     */
    public static Object fromJson(String json, Class classType) {
        try {
            return objectMapper.readValue(json, classType);
        } catch (IOException e) {
            log.error("【json转对象】错误, string={}", json);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json集合转对象集合
     *
     * @param json
     * @param typeReference
     * @return
     */
    public static Object fromJson(String json, TypeReference typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error("【json集合转对象集合】错误, string={}", json);
            e.printStackTrace();
        }
        return null;

    }
}