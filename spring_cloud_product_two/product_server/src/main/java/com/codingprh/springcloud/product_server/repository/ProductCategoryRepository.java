package com.codingprh.springcloud.product_server.repository;

import com.codingprh.springcloud.product_server.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author codingprh
 * @create 2018-12-24 5:48 PM
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

}
