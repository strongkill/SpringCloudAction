package com.codingprh.springcloud.product_server.enums;

import lombok.Getter;

/**
 * @author codingprh
 * @create 2018-12-27 5:45 PM
 */
@Getter
public enum ProductExceptionEnum {
    PRODUCT_NOT_EXIST(1000, "商品不存在"),
    INVENTORY_SHORTAGE(1001, "库存不足"),;
    private Integer code;
    private String msg;

    ProductExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
