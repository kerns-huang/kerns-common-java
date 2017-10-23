/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */


package com.huiguan.boot.idmarket;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局锁自动配置类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 上午11:02 $$
 */
@Configuration
@EnableConfigurationProperties(IdMarketProperties.class)
public class IdMarketAutoConfiguration {

    @Autowired
    private IdMarketProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public IdMarket idMarket() {
        Preconditions.checkArgument(null != properties);
        Preconditions.checkArgument(properties.getWorkerId() > 0L);
        IdMarket idMarket = new IdMarketImpl(properties.getWorkerId());
        IdMarkets.setIdMarket(idMarket);
        return idMarket;
    }

}
