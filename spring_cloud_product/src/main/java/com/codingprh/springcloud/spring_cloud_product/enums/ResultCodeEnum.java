package com.codingprh.springcloud.spring_cloud_product.enums;

import lombok.Getter;

/**
 * @author codingprh
 * @create 2018-12-25 3:40 PM
 */
@Getter
public enum ResultCodeEnum {
    //1xx(临时响应)表示临时响应并需要请求者继续执行操作的状态代码
    TEMPORARY_RESPONSE(100, "临时响应"),
    //2xx (成功)表示成功处理了请求的状态代码
    SUCESS(200, "成功"),
    //3xx (重定向) 表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向
    REDIRECT(300, "重定向"),
    //4xx(请求错误) 这些状态代码表示请求可能出错，妨碍了服务器的处理
    REQUEST_ERROR(400, "请求错误"),
    //5xx(服务器错误)这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错
    SERVER_ERROR(500,"服务器异常"),
    ;
    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
