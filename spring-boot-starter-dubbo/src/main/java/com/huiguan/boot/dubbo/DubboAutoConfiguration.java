/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */


package com.huiguan.boot.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.huiguan.boot.dubbo.endpoint.DubboEndpoint;
import com.huiguan.boot.dubbo.endpoint.DubboOperationEndpoint;
import com.huiguan.boot.dubbo.health.DubboHealthIndicator;
import com.huiguan.boot.dubbo.metrics.DubboMetrics;
import com.huiguan.boot.dubbo.runners.DubboStartupRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo自动配置类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {
    @Autowired
    private DubboProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig dubboApplicationConfig() {
        ApplicationConfig appConfig = new ApplicationConfig();
        appConfig.setName(this.properties.getAppname());
        return appConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig dubboProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(this.properties.getProtocol());
        protocolConfig.setPort(this.properties.getPort());
        protocolConfig.setThreads(this.properties.getThreads());
        return protocolConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig dubboRegistryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(this.properties.getRegistry());
        registryConfig.setGroup(properties.getRegistrygroup());
        return registryConfig;
    }

    @Bean
    public DubboHealthIndicator dubboHealthIndicator() {
        return new DubboHealthIndicator();
    }

    @Bean
    public DubboEndpoint dubboEndpoint() {
        return new DubboEndpoint();
    }

    @Bean
    public DubboMetrics dubboConsumerMetrics() {
        return new DubboMetrics();
    }


    @Bean
    public DubboOperationEndpoint dubboOperationEndpoint() {
        return new DubboOperationEndpoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public DubboStartupRunner dubboStartupRunner() {
        DubboStartupRunner dubboStartupRunner = new DubboStartupRunner();
        dubboStartupRunner.setAppName(this.properties.getAppname());
        return dubboStartupRunner;
    }
}
