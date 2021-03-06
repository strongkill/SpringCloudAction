package com.codingprh.springcloud.spring_cloud_product.VO;

import com.codingprh.springcloud.spring_cloud_product.enums.ResultCodeEnum;
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
}