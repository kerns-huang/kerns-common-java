package com.xd.elasticsearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * elastic search 配置相关的链接
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:13
 **/
@ConfigurationProperties(prefix = "spring.data.es")
public class EsProperties {
    /**
     * 和 es的链接方式 ，目前使用rest api的方式调用
     */
    private String url;
}
