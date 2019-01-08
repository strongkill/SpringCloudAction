package com.codingprh.springcloud.product_server.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述:
 * 最终商品列表返回的vo对象
 *
 * @author codingprh
 * @create 2018-12-25 3:17 PM
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;
    
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}