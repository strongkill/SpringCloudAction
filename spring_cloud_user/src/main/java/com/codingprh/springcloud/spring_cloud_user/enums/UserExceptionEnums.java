package com.codingprh.springcloud.spring_cloud_user.enums;

import lombok.Getter;

/**
 * 用户枚举
 *
 * @author codingprh
 * @create 2019-01-13 12:32 PM
 */
@Getter
public enum UserExceptionEnums {
    LOGIN_FAIL(1000, "登录失败：账号或者密码错误"),
    ROLE_ERROR(1001, "角色错误"),;

    private final Integer code;
    private final String msg;

    UserExceptionEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
