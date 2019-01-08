package com.codingprh.springcloud.order_server.enums;

import lombok.Getter;

/**
 * 付款状态枚举
 *
 * @author codingprh
 * @create 2018-12-27 5:13 PM
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    FINISH(1, "完成支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
