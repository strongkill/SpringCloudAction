package com.codingprh.springcloud.spring_cloud_user.controller;

import com.codingprh.common.spring_cloud_common.entity.ResultVO;
import com.codingprh.common.spring_cloud_common.utils.ResultVOUtils;
import com.codingprh.springcloud.spring_cloud_user.entity.UserInfo;
import com.codingprh.springcloud.spring_cloud_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 用户控制器
 *
 * @author codingprh
 * @create 2019-01-28 3:36 PM
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserService userService;

    /**
     * 通过用户的openid，获取用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public ResultVO getUserInfo(@RequestParam("openid") String openid) {
        UserInfo userInfo = userService.findByOpenid(openid);
        return ResultVOUtils.success(userInfo);
    }

}