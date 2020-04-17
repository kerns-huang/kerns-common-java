package com.xd.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * elastic search 配置相关的链接
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:13
 **/
@ConfigurationProperties(prefix = "xd.data.es")
@Data
public class EsProperties {
    /**
     * 和 es的链接方式 ，目前使用rest api的方式调用
     */
    private String host;
    /**
     * 返回端口
     */
    private String port;
    /**
     * 返回结果
     */
    private String responseType;
}
