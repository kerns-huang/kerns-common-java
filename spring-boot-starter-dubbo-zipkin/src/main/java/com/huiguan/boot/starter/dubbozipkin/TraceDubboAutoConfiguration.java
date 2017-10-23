/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.starter.dubbozipkin;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.SpanExtractor;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * 入口类(自动配置)
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/17 下午1:42 $$
 */


@Configuration
@ConditionalOnProperty(value = "spring.dubbo.trace.enabled", matchIfMissing = true)
@ConditionalOnClass(Filter.class)
@AutoConfigureAfter(TraceAutoConfiguration.class)
public class TraceDubboAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextAwareBean applicationContextAwareBean() {
        return new ApplicationContextAwareBean();
    }


    @Bean
    public SpanInjector<RpcContext> dubboSpanInjector() {
        return new DubboSpanInjector();
    }


    @Bean
    public SpanExtractor<RpcContext> dubboSpanExtractor(Random random) {
        return new DubboSpanExtractor(random);
    }
}
