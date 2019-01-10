package com.codingprh.springcloud.product_server.message;


import com.codingprh.common.spring_cloud_common.utils.JsonUtil;
import com.codingprh.springcloud.order_client.client.OrderClient;
import com.codingprh.springcloud.order_common.message.OrderMessage;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import com.codingprh.springcloud.product_server.service.ProductInfoService;
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
    @Autowired
    private OrderClient orderClient;

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

    @RabbitListener(queuesToDeclare = @Queue("orderWaitSync"))
    public void processSync(String message) {
        try {
            OrderMessage orderMessage = (OrderMessage) JsonUtil.fromJson(message, OrderMessage.class);
            //db减少库存
            productInfoService.decreaseStock(orderMessage.getDecreaseStockInput());
            //订单入库
            orderClient.createOrder(orderMessage);
        } catch (Exception e) {
            //当程序出错的时候，不catch异常会一直取调用方法 processSync方法
            e.printStackTrace();
        }
    }

}