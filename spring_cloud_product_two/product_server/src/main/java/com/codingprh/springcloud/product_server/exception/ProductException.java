package com.codingprh.springcloud.product_server.exception;


import com.codingprh.springcloud.product_server.enums.ProductExceptionEnum;

/**
 * 描述:
 * 商品异常
 *
 * @author codingprh
 * @create 2018-12-27 5:42 PM
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ProductException(ProductExceptionEnum productExceptionEnum) {
        super(productExceptionEnum.getMsg());
        this.code = productExceptionEnum.getCode();
    }
}