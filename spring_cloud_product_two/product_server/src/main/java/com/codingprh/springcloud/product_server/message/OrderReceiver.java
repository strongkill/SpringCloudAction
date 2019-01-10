package com.codingprh.springcloud.product_server.message;


import com.codingprh.common.spring_cloud_common.utils.JsonUtil;
import com.codingprh.common.spring_cloud_common.utils.KeysUtils;
import com.codingprh.springcloud.order_common.message.OrderMessage;
import com.codingprh.springcloud.product_common.entity.ProductInfoOutput;
import com.codingprh.springcloud.product_server.service.ProductInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

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

    @RabbitListener(queuesToDeclare = @Queue("orderWaitSync"))
    public void processSync(String message) {
        OrderMessage orderMessage = (OrderMessage) JsonUtil.fromJson(message, OrderMessage.class);
        //db减少库存
        
        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetailList) {
            for (ProductInfoOutput productInfo : productInfoOutputList) {
                if (Objects.equals(orderDetail.getProductId(), productInfo.getProductId())) {
                    //计算单价
                    orderAmout = new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice()).add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeysUtils.generateUniqueKey());
                }
            }
        }


        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //异步订单入库：直接穿透db
        threadPoolTaskExecutor.submit(() -> {
            orderDetailRepository.saveAll(orderDetailList);
            orderMasterRepository.save(orderMaster);
        });
        
        //订单入库

    }

}