package com.codingprh.springcloud.spring_cloud_order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 描述:
 * 订单_商品详细表
 *
 * @author codingprh
 * @create 2018-12-25 5:21 PM
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;

    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 商品id.
     */
    private String productId;

    /**
     * 商品名称.
     */
    private String productName;

    /**
     * 商品单价.
     */
    private BigDecimal productPrice;

    /**
     * 商品数量.
     */
    private Integer productQuantity;

    /**
     * 商品小图.
     */
    private String productIcon;

}