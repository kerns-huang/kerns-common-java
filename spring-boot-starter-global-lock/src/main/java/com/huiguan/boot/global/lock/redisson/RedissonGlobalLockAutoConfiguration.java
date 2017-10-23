/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */


package com.huiguan.boot.global.lock.redisson;

import com.huiguan.boot.global.lock.GlobalLockAutoConfiguration;
import com.huiguan.boot.global.lock.GlobalLockFactory;
import com.huiguan.boot.global.lock.GlobalLockProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局锁自动配置类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 上午11:02 $$
 */
@Configuration
//@ConditionalOnProperty(value = "spring.globallock.drivername", matchIfMissing =false)
@ConditionalOnExpression("'${spring.globallock.drivername}'.equals('redisson')")
@AutoConfigureAfter(GlobalLockAutoConfiguration.class)
public class RedissonGlobalLockAutoConfiguration {
    @Autowired
    private GlobalLockProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public Config redissonConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress(properties.getServers());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient() {
        RedissonClient redisson = Redisson.create(redissonConfig());
        return redisson;
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalLockFactory globalLockFactory() {
        RedissonGlobalLockFactory globalLockFactory = new RedissonGlobalLockFactory(redissonClient());
        return globalLockFactory;
    }

}
