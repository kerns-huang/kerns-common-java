package com.xd.web.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xd.web.jackson.BooleanSerializer;
import com.xd.web.jackson.ImageUrlSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web 序列化配置
 * @author xiaohei
 * @create 2019-12-20 下午5:15
 **/
@Configuration
public class JsonConfig {
    @Bean
    public SimpleModule simpleModule(){
        SimpleModule simpleModule=new SimpleModule();
        simpleModule.addSerializer(Boolean.class,new BooleanSerializer());
        simpleModule.addSerializer(Number.class,new ToStringSerializer());
        simpleModule.addSerializer(String.class,new ImageUrlSerializer("12312"));
        return simpleModule;
    }
}
