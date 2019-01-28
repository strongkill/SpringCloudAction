package com.codingprh.springcloud.spring_cloud_gateway.filter;

import com.codingprh.common.spring_cloud_common.constant.CookieConstant;
import com.codingprh.common.spring_cloud_common.utils.CookieUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 描述:
 * 用户登录认证
 *
 * @author codingprh
 * @create 2019-01-28 2:40 PM
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String requestUrl = request.getRequestURI();
        //简单粗暴：登录请求不走认证：/myUser/login/buyer
        if (requestUrl.contains("/login/buyer") || requestUrl.contains("/login/seller")) {
            return null;
        }
        if (Objects.isNull(CookieUtils.get(request, CookieConstant.TOKEN)) && Objects.isNull(CookieUtils.get(request, CookieConstant.OPENID))) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

            requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            requestContext.setResponseBody("请先访问登录接口");
            return requestContext.getResponse();
        }
        return null;
    }
}