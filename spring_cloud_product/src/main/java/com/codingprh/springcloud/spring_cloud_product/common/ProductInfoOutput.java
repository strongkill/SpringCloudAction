package com.codingprh.springcloud.spring_cloud_product.common;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述:
 * 暴露给订单使用的productInfo字段
 *
 * @author codingprh
 * @create 2018-12-27 3:07 PM
 */
@Data
public class ProductInfoOutput implements Serializable {
    private String productId;

    /**
     * 名字.
     */
    private String productName;

    /**
     * 单价.
     */
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    private Integer productStock;

    /**
     * 描述.
     */
    private String productDescription;

    /**
     * 小图.
     */
    private String productIcon;

    /**
     * 状态, 0正常1下架.
     */
    private Integer productStatus;

    /**
     * 类目编号.
     */
    private Integer categoryType;

}