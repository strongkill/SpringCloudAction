package com.codingprh.springcloud.spring_cloud_product.client;

import com.codingprh.springcloud.spring_cloud_product.common.DecreaseStockInput;
import com.codingprh.springcloud.spring_cloud_product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 描述:
 * feign:提供给订单服务的接口
 *
 * @author codingprh
 * @create 2018-12-27 2:49 PM
 */
@FeignClient(name = "product", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    class ProductClientFallback implements ProductClient {

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }


}