/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */


package com.huiguan.boot.trace.sql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * trace-sql自动配置类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/6 上午11:02 $$
 */
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@EnableConfigurationProperties(TraceSqlProperties.class)
public class TraceSqlAutoConfiguration implements ConfigurationCustomizer {

    @Autowired
    private Tracer tracer;

    @Override
    public void customize(org.apache.ibatis.session.Configuration configuration) {
        configuration.addInterceptor(new TraceSqlInterceptor(tracer));
    }
}
