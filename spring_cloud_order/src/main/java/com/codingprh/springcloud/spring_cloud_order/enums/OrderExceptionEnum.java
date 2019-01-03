package com.codingprh.springcloud.spring_cloud_order.enums;

import lombok.Getter;

/**
 * @author codingprh
 * @create 2018-12-26 2:51 PM
 */
@Getter
public enum OrderExceptionEnum {
    FORM_PARAM_ERROR(1000, "表单参数错误"),

    SHOPPING_CART_EMPTY(10001, "购物车信息为空"),

    OBJECT_CONVERTER_MAP_ERROR(10002, "对象转RedisMap发生错误"),

    MAP_CONVERTER_OBJECT_ERROR(10003, "RedisMap转对象发生错误"),

    PRODUCT_NOT_FOUND(10004, "商品不存在"),

    INVENTORY_SHORTAGE(1005, "库存不足");
    private Integer code;
    private String msg;

    OrderExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
