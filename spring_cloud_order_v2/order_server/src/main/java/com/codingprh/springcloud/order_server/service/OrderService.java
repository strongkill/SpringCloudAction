package com.codingprh.springcloud.order_server.service;


import com.codingprh.springcloud.order_common.message.OrderMessage;
import com.codingprh.springcloud.order_server.dto.OrderDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述:
 * 订单业务
 *
 * @author codingprh
 * @create 2018-12-25 5:31 PM
 */
public interface OrderService {
    /**
     * v1：创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * v2：异步创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO createSync(OrderDTO orderDTO);

    void mqCreateOrder(OrderMessage orderMessage);


}