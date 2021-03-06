package com.codingprh.springcloud.spring_cloud_order.controller;

import com.codingprh.springcloud.spring_cloud_order.VO.ResultVO;
import com.codingprh.springcloud.spring_cloud_order.converter.OrderForm2OrderDTOConverter;
import com.codingprh.springcloud.spring_cloud_order.dto.OrderDTO;
import com.codingprh.springcloud.spring_cloud_order.enums.OrderExceptionEnum;
import com.codingprh.springcloud.spring_cloud_order.exception.OrderException;
import com.codingprh.springcloud.spring_cloud_order.form.OrderForm;
import com.codingprh.springcloud.spring_cloud_order.service.OrderService;
import com.codingprh.springcloud.spring_cloud_order.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
}