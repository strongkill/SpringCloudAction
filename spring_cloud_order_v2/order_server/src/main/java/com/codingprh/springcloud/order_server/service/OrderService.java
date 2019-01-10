package com.codingprh.springcloud.order_server.service;


import com.codingprh.springcloud.order_server.dto.OrderDTO;

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

    OrderDTO createSync(OrderDTO orderDTO);


}