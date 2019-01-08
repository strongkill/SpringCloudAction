package com.codingprh.springcloud.product_server.service;


import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import com.codingprh.springcloud.product_server.entity.ProductInfo;

import java.util.List;

/**
 * 描述:
 * productInfo的业务层
 *
 * @author codingprh
 * @create 2018-12-24 5:52 PM
 */

public interface ProductInfoService {
    /**
     * 查询所有上线的商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据productId集合查找商品集合
     *
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 减库存操作
     *
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);

    /**
     * 更新存款
     *
     * @param productInfoOutputs
     */
    void updateStock(List<ProductInfoOutput> productInfoOutputs);
}