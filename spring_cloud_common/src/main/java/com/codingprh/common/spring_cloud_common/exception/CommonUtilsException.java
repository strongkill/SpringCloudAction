package com.codingprh.common.spring_cloud_common.exception;

/**
 * 描述:
 * common模块的异常
 *
 * @author codingprh
 * @create 2019-01-08 5:29 PM
 */
public class CommonUtilsException extends RuntimeException {
    public CommonUtilsException() {
    }

    public CommonUtilsException(String message) {
        super(message);
    }
}