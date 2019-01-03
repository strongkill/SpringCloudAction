package com.codingprh.springcloud.spring_cloud_product.repository;

import com.codingprh.springcloud.spring_cloud_product.SpringCloudProductApplicationTests;
import com.codingprh.springcloud.spring_cloud_product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author codingprh
 * @create 2018-12-25 2:14 PM
 */

public class ProductInfoRepositoryTest extends SpringCloudProductApplicationTests {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findAllByProductStatusIn() {
        List<ProductInfo> infoList = productInfoRepository.findAllByProductStatusIn(0);
        Assert.assertTrue(infoList.size() > 0);
    }
}