package com.codingprh.springcloud.order_server.controller;


import com.codingprh.common.spring_cloud_common.entity.ResultVO;
import com.codingprh.common.spring_cloud_common.utils.ResultVOUtils;
import com.codingprh.springcloud.order_common.message.OrderMessage;
import com.codingprh.springcloud.order_server.converter.OrderForm2OrderDTOConverter;
import com.codingprh.springcloud.order_server.dto.OrderDTO;
import com.codingprh.springcloud.order_server.enums.OrderExceptionEnum;
import com.codingprh.springcloud.order_server.exception.OrderException;
import com.codingprh.springcloud.order_server.form.OrderForm;
import com.codingprh.springcloud.order_server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 订单控制器
 *
 * @author codingprh
 * @create 2018-12-25 6:01 PM
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 第一版：在订单服务创建订单，库存已redis的数据为准。
     * 然后通过发mq消息到db，同步库存。
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new OrderException(OrderExceptionEnum.FORM_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.oderForm2OrderDTO(orderForm);
        if (orderDTO.getOrderDetailList().isEmpty()) {
            log.error("[创建订单]购物车信息为空，OrderDTO={}", orderDTO);
            throw new OrderException(OrderExceptionEnum.SHOPPING_CART_EMPTY);
        }
        Map<String, String> result = new HashMap<>();
        result.put("order", orderService.create(orderDTO).getOrderId());

        return ResultVOUtils.success(result);
    }

    @PostMapping("/create/sync")
    public ResultVO<Map<String, String>> create_sync_v2(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new OrderException(OrderExceptionEnum.FORM_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.oderForm2OrderDTO(orderForm);
        if (orderDTO.getOrderDetailList().isEmpty()) {
            log.error("[创建订单]购物车信息为空，OrderDTO={}", orderDTO);
            throw new OrderException(OrderExceptionEnum.SHOPPING_CART_EMPTY);
        }
        Map<String, String> result = new HashMap<>();
        result.put("order", orderService.createSync(orderDTO).getOrderId());

        return ResultVOUtils.success(result);

    }

    /**
     * mq异步下单的流程
     *
     * @param orderMessage
     */
    @PostMapping("/createOrder")
    public void createOrder(@RequestBody OrderMessage orderMessage) {
        orderService.mqCreateOrder(orderMessage);
    }


}