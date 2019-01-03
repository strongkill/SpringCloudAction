package com.codingprh.springcloud.spring_cloud_product.message;

import com.codingprh.springcloud.spring_cloud_product.common.ProductInfoOutput;
import com.codingprh.springcloud.spring_cloud_product.service.ProductInfoService;
import com.codingprh.springcloud.spring_cloud_product.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 * 订单处理队列
 *
 * @author codingprh
 * @create 2019-01-02 10:44 AM
 */
@Component
@Slf4j
public class OrderReceiver {
    @Autowired
    private ProductInfoService productInfoService;

    //todo:如果reds的库存和db的库存不一致，需要如何处理
    @RabbitListener(queuesToDeclare = @Queue("orderWait"))
    public void process(String message) {
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {
                });
        //todo:查找库存：调用订单服务更新订单状态，||在这里的时候才创建订单(调用服务)
        productInfoService.updateStock(productInfoOutputList);
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutputList);

    }

}