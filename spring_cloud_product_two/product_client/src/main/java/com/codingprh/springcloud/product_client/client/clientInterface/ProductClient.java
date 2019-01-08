package com.codingprh.springcloud.product_client.client.clientInterface;


import com.codingprh.springcloud.product_client.client.fallback.ProductClientFallback;
import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient(name = "product", fallback = ProductClientFallback.class)
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
    


}