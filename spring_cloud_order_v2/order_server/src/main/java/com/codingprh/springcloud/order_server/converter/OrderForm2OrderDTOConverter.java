package com.codingprh.springcloud.order_server.converter;


import com.codingprh.springcloud.order_server.dto.OrderDTO;
import com.codingprh.springcloud.order_server.entity.OrderDetail;
import com.codingprh.springcloud.order_server.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 将前端传递对象，orderForm转换为orderDTO对象
 *
 * @author codingprh
 * @create 2018-12-26 3:00 PM
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO oderForm2OrderDTO(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        //采用fastjson来处理json数据
//        orderDetailList = (List<OrderDetail>) JsonUtil.fromJson(orderForm.getItems(), new TypeReference<List<OrderDetail>>() {
//        });
        //采用gson来处理一个前端数组封装成为对象
        //todo：考虑使用fastjson来处理
        orderDetailList = gson.fromJson(orderForm.getItems(),
                new TypeToken<List<OrderDetail>>() {
                }.getType());
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}