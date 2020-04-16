package com.xd.elasticsearch.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * es的 实体类 生成
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:22
 **/
@Configuration
@EnableConfigurationProperties(EsProperties.class)
public class EsConfig {
}
