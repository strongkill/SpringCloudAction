package com.codingprh.springcloud.spring_cloud_order.VO;

import com.codingprh.springcloud.spring_cloud_order.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 描述:
 * 返回给前端的最后一层
 *
 * @author codingprh
 * @create 2018-12-25 6:04 PM
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