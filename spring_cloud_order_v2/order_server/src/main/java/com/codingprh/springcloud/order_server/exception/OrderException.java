package com.codingprh.springcloud.order_server.exception;


import com.codingprh.springcloud.order_server.enums.OrderExceptionEnum;

/**
 * 描述:
 * 订单异常
 *
 * @author codingprh
 * @create 2018-12-26 2:49 PM
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public OrderException(OrderExceptionEnum orderExceptionEnum) {
        super(orderExceptionEnum.getMsg());
        this.code = orderExceptionEnum.getCode();
    }


}