package com.codingprh.springcloud.spring_cloud_gateway.filter;

import com.codingprh.springcloud.spring_cloud_gateway.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;


/**
 * 描述:
 * 限流拦截器
 *
 * @author codingprh
 * @create 2019-01-03 10:13 AM
 */
@Component
public class RateLimitFilter extends ZuulFilter {
    //每秒通过2个请求
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException();
        }
        return null;
    }
}