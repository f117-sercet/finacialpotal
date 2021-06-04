package com.epdemic.srm.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 跨域调用设置
 * @Author:estic
 * @Date: 2021/6/4 10:06
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter(){

        CorsConfiguration config = new CorsConfiguration();
        // 是否允许携带cookies
        config.setAllowCredentials(true);
        // 可接受的域
        config.addAllowedOrigin("*");
        // 允许携带的请求头
        config.addAllowedHeader("*");
        // 允许访问的方式
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);

    }
}
