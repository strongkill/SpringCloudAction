package com.codingprh.common.spring_cloud_common.entity;


import com.codingprh.common.spring_cloud_common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 描述:
 * 暴露api的最外层封装
 *
 * @author codingprh
 * @create 2018-12-24 5:40 PM
 */
@Data
public class ResultVO<T> {
    private Integer code;

    private String msg;

    private T data;

    public ResultVO() {
    }


    public ResultVO(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }
    public ResultVO(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }
}