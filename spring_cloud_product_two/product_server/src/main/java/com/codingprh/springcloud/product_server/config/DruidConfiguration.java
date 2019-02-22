package com.codingprh.springcloud.product_server.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解是用来配置SpringBoot项目的配置注解，如果将该注解配置在实体类上，该类内的所有bean以及配置都会应用于全局。
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        // 创建servlet注册实体
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 设置IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 设置IP黑名单，deny优先级高于allow
        servletRegistrationBean.addInitParameter("deny", "192.168.5.100");
        // 设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> statFilter() {
        // 创建过滤器
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        // 设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}