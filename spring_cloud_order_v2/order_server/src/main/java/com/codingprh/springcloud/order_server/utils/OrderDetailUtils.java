package com.codingprh.springcloud.order_server.utils;

import com.codingprh.springcloud.order_server.entity.OrderDetail;
import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 订单详情工具类
 *
 * @author codingprh
 * @create 2019-01-10 2:07 PM
 */
public class OrderDetailUtils {
    public static List<DecreaseStockInput> OrderDeatils2DecreaseStockInputs(List<OrderDetail> orderDetails) {
        List<DecreaseStockInput> decreaseStockInputList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
            BeanUtils.copyProperties(orderDetail, decreaseStockInput);
            decreaseStockInputList.add(decreaseStockInput);
        }
        return decreaseStockInputList;
    }

    public static List<OrderDetail> DecreaseStockInputs2OrderDeatils(List<DecreaseStockInput> decreaseStockInputs) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputs) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(decreaseStockInput, orderDetail);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}