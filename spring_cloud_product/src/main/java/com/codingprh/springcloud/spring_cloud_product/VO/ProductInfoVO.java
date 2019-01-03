package com.codingprh.springcloud.spring_cloud_product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author codingprh
 * @create 2018-12-25 3:20 PM
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
    
}