package com.codingprh.springcloud.spring_cloud_product.repository;

import com.codingprh.springcloud.spring_cloud_product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author codingprh
 * @create 2018-12-24 5:48 PM
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

}
