package com.xd.elasticsearch.config;

import com.xd.elasticsearch.ElasticSearchClient;
import com.xd.elasticsearch.core.EsOperations;
import com.xd.elasticsearch.core.EsTemplate;
import com.xd.elasticsearch.core.response.JSONResponseResolver;
import com.xd.elasticsearch.core.response.ResponseResover;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * es的 实体类 生成
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:22
 **/
@Configuration
@EnableConfigurationProperties(EsProperties.class)
public class EsAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public EsProperties esProperties() {
        return new EsProperties();
    }
    @Bean
    public EsOperations esOperations(EsProperties esProperties){
        ElasticSearchClient client=new ElasticSearchClient(buildUrl(esProperties));
        ResponseResover responseResover=new JSONResponseResolver();
        EsTemplate esTemplate=new EsTemplate(client,responseResover);
        return esTemplate;
    }

    /**
     * 构建返回结果
     * @param esProperties
     * @return
     */
    private String buildUrl(EsProperties esProperties){
        return "http://"+esProperties.getHost()+":"+esProperties.getPort()+"/_sql?format="+esProperties.getResponseType();
    }
}
