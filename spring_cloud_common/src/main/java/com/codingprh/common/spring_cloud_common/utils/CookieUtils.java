package com.codingprh.common.spring_cloud_common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 描述:
 * cookie工具类
 *
 * @author codingprh
 * @create 2019-01-13 1:58 PM
 */
public class CookieUtils {

    public static void set(HttpServletResponse response, String name, String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(name, cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

}