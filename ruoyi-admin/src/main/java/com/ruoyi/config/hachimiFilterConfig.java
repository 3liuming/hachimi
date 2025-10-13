package com.ruoyi.config;


import com.ruoyi.Filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class hachimiFilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());  // 传入你的 JwtFilter 实例
        registrationBean.addUrlPatterns("/personal/*");  // 配置需要拦截的 URL 路径（根据实际情况修改）
        registrationBean.setOrder(1);  // 设置过滤器的优先级，数字越小，优先级越高
        return registrationBean;
    }
}