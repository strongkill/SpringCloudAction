package com.codingprh.common.spring_cloud_common.constant;

/**
 * @author codingprh
 * @create 2018-12-31 2:03 PM
 */
public interface RedisConstant {
    /**
     * 商品信息
     */
    String PRODUCT_TEMPLATE = "product:%s";
    /**
     * 完成抢购的商品集合
     */
    String PRODUCT_FINISH_SET = "PRODUCT_FINISH_SET";
    /**
     * 用户token
     */
    String TOKEN_TEMPLATE = "token_%s";
}
