package com.xd.jpa.conf;

import com.xd.jpa.util.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConf {

    @Bean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }
}
