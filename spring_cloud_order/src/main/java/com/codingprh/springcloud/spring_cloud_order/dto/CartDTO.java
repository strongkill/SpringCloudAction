package com.codingprh.springcloud.spring_cloud_order.dto;

import lombok.Data;

/**
 * 描述:
 * 创建下单：订单信息中的购物信息
 *
 * @author codingprh
 * @create 2018-12-26 4:18 PM
 */
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

}