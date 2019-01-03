package com.codingprh.springcloud.spring_cloud_order.utils;

import com.codingprh.springcloud.spring_cloud_order.VO.ResultVO;
import com.codingprh.springcloud.spring_cloud_order.enums.ResultCodeEnum;

/**
 * 描述:
 * resultVO返回对象的工具方法封装
 *
 * @author codingprh
 * @create 2018-12-25 3:37 PM
 */
public class ResultVOUtils {
    public static ResultVO success(Object object) {
        return new ResultVO(ResultCodeEnum.SUCESS, object);
    }

}