package com.codingprh.springcloud.product_server.repository;

import com.codingprh.springcloud.product_server.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * productInfo的dao层
 *
 * @author codingprh
 * @create 2018-12-24 5:46 PM
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findAllByProductStatusIn(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);

}