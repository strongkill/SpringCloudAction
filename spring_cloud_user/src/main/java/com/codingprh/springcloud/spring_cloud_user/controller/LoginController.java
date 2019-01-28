package com.codingprh.springcloud.spring_cloud_user.controller;

import com.codingprh.common.spring_cloud_common.constant.CookieConstant;
import com.codingprh.common.spring_cloud_common.constant.RedisConstant;
import com.codingprh.common.spring_cloud_common.entity.ResultVO;
import com.codingprh.common.spring_cloud_common.utils.CookieUtils;
import com.codingprh.common.spring_cloud_common.utils.ResultVOUtils;
import com.codingprh.springcloud.spring_cloud_user.entity.UserInfo;
import com.codingprh.springcloud.spring_cloud_user.enums.UserExceptionEnums;
import com.codingprh.springcloud.spring_cloud_user.enums.UserRoleEnums;
import com.codingprh.springcloud.spring_cloud_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 登录接口
 *
 * @author codingprh
 * @create 2019-01-13 12:24 PM
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 买家登录
     *
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyerLogin(@RequestParam("openid") String openid, HttpServletResponse response) {
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtils.error(UserExceptionEnums.LOGIN_FAIL.getMsg());
        }
        if (!Objects.equals(userInfo.getRole(), UserRoleEnums.BUYER.getCode())) {
            return ResultVOUtils.error(UserExceptionEnums.ROLE_ERROR.getMsg());
        }

        CookieUtils.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVOUtils.success();
    }

    /**
     * 卖家登录
     *
     * @return
     */
    @GetMapping("/seller")
    public ResultVO sellerLogin(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);
        //判断用户是否登录过
        if (Objects.nonNull(cookie) && !StringUtils.isEmpty(redisTemplate.opsForValue().get(
                String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtils.success();
        }

        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtils.error(UserExceptionEnums.LOGIN_FAIL.getMsg());
        }
        if (!Objects.equals(userInfo.getRole(), UserRoleEnums.SELLER.getCode())) {
            return ResultVOUtils.error(UserExceptionEnums.ROLE_ERROR.getMsg());
        }
        //加入缓存并且设置cookie
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid, CookieConstant.expire, TimeUnit.SECONDS);
        CookieUtils.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVOUtils.success();
    }




}