package com.xd.web.config;

import com.xd.web.interceptor.RequestContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls=new ArrayList<>();
        urls.add("/doc.html");
        urls.add("/webjars/**");
        urls.add("/statics/**");
        urls.add("/swagger-resources");
        urls.add("/error");
        urls.add("/swagger-ui.html");
        registry.addInterceptor(new RequestContextInterceptor()).addPathPatterns("/**").excludePathPatterns(urls);
    }
}
