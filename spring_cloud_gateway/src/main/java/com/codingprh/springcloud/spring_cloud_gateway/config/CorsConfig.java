package com.codingprh.springcloud.spring_cloud_gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 描述:
 * 跨域配置
 * C - Cross  O - Origin  R - Resource  S - Sharing
 *
 * @author codingprh
 * @create 2019-01-03 9:32 AM
 */
@Configuration
public class CorsConfig {
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        //配置允许通过的ip地址：http:www.a.com
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setMaxAge(300L);

        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}