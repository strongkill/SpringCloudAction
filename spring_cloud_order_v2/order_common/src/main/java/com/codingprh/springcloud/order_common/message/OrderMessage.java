package com.codingprh.springcloud.order_common.message;

import com.codingprh.springcloud.product_common.entity.DecreaseStockInput;
import lombok.Data;

import java.util.List;


/**
 * 描述:
 * 前端传来的参数实体：订单form
 *
 * @author codingprh
 * @create 2018-12-25 5:58 PM
 */
@Data
public class OrderMessage {
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;
    /**
     * 减库存信息
     */
    private List<DecreaseStockInput> DecreaseStockInput;

}