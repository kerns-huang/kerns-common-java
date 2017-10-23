/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */


package com.huiguan.boot.global.lock;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 全局锁自动配置类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 上午11:02 $$
 */
@Configuration
@EnableConfigurationProperties(GlobalLockProperties.class)
@ConditionalOnBean(annotation = EnableGlobalLockConfiguration.class)
public class GlobalLockAutoConfiguration {
}
