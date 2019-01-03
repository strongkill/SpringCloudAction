package com.codingprh.springcloud.spring_cloud_product.service.impl;

import com.codingprh.springcloud.spring_cloud_product.entity.ProductCategory;
import com.codingprh.springcloud.spring_cloud_product.repository.ProductCategoryRepository;
import com.codingprh.springcloud.spring_cloud_product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author codingprh
 * @create 2018-12-25 2:35 PM
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return productCategoryRepository.findByCategoryTypeIn(types);
    }
}