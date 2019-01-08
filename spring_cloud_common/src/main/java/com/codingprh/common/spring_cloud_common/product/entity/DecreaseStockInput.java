package com.codingprh.common.spring_cloud_common.product.entity;

import lombok.Data;

/**
 * 描述:
 * 暴露给订单使用的：减少库存数量
 *
 * @author codingprh
 * @create 2018-12-27 3:08 PM
 */
@Data
public class DecreaseStockInput {
    private String productId;

    private Integer productQuantity;

}