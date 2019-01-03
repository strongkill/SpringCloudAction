package com.codingprh.springcloud.product_client.client.fallback;

import com.codingprh.springcloud.product_client.client.clientInterface.ProductClient;
import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 * fallback集合
 *
 * @author codingprh
 * @create 2019-01-03 5:10 PM
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}