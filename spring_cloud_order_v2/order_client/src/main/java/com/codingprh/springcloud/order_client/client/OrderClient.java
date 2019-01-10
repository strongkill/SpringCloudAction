package com.codingprh.springcloud.order_client.client;

import com.codingprh.springcloud.order_common.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述:
 * 暴露的下单接口
 *
 * @author codingprh
 * @create 2019-01-09 6:48 PM
 */
@FeignClient(name = "order", fallback = OrderClient.OrderClientFallback.class)

public interface OrderClient {

    @PostMapping("/order/createOrder")
    void createOrder(@RequestBody OrderMessage orderMessage);

    @Component
    @Slf4j
    class OrderClientFallback implements OrderClient {

        @Override
        public void createOrder(@RequestBody OrderMessage orderMessage) {
            log.error("现在下订单繁忙");
        }
    }

}