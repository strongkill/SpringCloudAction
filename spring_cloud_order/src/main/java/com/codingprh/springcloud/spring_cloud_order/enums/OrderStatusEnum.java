package com.codingprh.springcloud.spring_cloud_order.enums;

import lombok.Getter;

/**
 * 描述:
 * 订单状态枚举
 *
 * @author codingprh
 * @create 2018-12-27 5:10 PM
 */
@Getter
public enum OrderStatusEnum {
    WAIT(0, "待处理订单"),
    
    NEW(1, "新订单"),

    FINISH(2, "完结订单"),

    CANCEL(3, "取消订单");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}