package com.codingprh.common.spring_cloud_common.utils;


import com.codingprh.common.spring_cloud_common.entity.ResultVO;
import com.codingprh.common.spring_cloud_common.enums.ResultCodeEnum;

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