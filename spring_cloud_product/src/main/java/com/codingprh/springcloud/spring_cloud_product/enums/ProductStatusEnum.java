package com.codingprh.springcloud.spring_cloud_product.enums;

import lombok.Getter;

/**
 * @author codingprh
 * @create 2018-12-25 11:42 AM
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "上线"),
    DOWN(1, "下线"),;
    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
