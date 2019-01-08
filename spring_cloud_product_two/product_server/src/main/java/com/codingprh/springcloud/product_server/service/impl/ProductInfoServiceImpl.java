package com.codingprh.springcloud.product_server.service.impl;


import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import com.codingprh.springcloud.product_server.entity.ProductInfo;
import com.codingprh.springcloud.product_server.enums.ProductExceptionEnum;
import com.codingprh.springcloud.product_server.enums.ProductStatusEnum;
import com.codingprh.springcloud.product_server.exception.ProductException;
import com.codingprh.springcloud.product_server.repository.ProductInfoRepository;
import com.codingprh.springcloud.product_server.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author codingprh
 * @create 2018-12-25 11:34 AM
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findAllByProductStatusIn(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        List<ProductInfoOutput> result = productInfoRepository.findByProductIdIn(productIdList).stream().map(
                e -> {
                    ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, productInfoOutput);
                    return productInfoOutput;
                }
        ).collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new ProductException(ProductExceptionEnum.PRODUCT_NOT_EXIST);
        }
        return result;
    }

    @Override
    @Transactional
    /**
     * todo：分布式锁
     */
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            String productId = decreaseStockInput.getProductId();
            //判断商品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(productId);
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ProductExceptionEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer stackResult = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (stackResult < 0) {
                throw new ProductException(ProductExceptionEnum.INVENTORY_SHORTAGE);
            }

            productInfo.setProductStock(stackResult);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void updateStock(List<ProductInfoOutput> productInfoOutputs) {
        for (ProductInfoOutput productInfoOutput : productInfoOutputs) {
            ProductInfo productInfo = new ProductInfo();
            BeanUtils.copyProperties(productInfoOutput, productInfo);
            productInfoRepository.save(productInfo);
        }
    }


}