package org.bill.demoproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class GlobalCorsConfig {
    //配置全局跨域
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许的域名
        config.addAllowedOriginPattern("*");
        //允许的请求头
        config.addAllowedHeader("*");
        //允许的请求方法
        config.addAllowedMethod("*");
        //允许携带cookie
        config.setAllowCredentials(true);
        //有效期
        config.setMaxAge(3600L);
        //添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        //返回新的CorsFilter
        return new CorsFilter(source);
    }
}
