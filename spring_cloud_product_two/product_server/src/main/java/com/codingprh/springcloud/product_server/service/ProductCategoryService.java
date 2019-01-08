package com.codingprh.springcloud.product_server.service;


import com.codingprh.springcloud.product_server.entity.ProductCategory;

import java.util.List;

/**
 * 描述:
 * 商品类目的业务
 *
 * @author codingprh
 * @create 2018-12-24 5:55 PM
 */
public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

}