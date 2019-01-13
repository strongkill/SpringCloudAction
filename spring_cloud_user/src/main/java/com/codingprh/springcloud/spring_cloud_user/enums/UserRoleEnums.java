package com.codingprh.springcloud.spring_cloud_user.enums;

import com.codingprh.common.spring_cloud_common.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 用户角色枚举
 *
 * @author codingprh
 * @create 2019-01-13 12:37 PM
 */
@Getter
public enum UserRoleEnums  {
    BUYER(1, "买家"),
    SELLER(2, "卖家");
    private final Integer code;
    private final String msg;

    UserRoleEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
