package com.codingprh.springcloud.order_common.message;

import lombok.Data;


/**
 * 描述:
 * 前端传来的参数实体：订单form
 *
 * @author codingprh
 * @create 2018-12-25 5:58 PM
 */
@Data
public class OrderMessage {
    /**
     * 买家姓名
     */
    private String name;

    /**
     * 买家手机号
     */
    private String phone;

    /**
     * 买家地址
     */
    private String address;

    /**
     * 买家微信openid
     */
    private String openid;

    /**
     * 购物车
     */
    private String items;

}