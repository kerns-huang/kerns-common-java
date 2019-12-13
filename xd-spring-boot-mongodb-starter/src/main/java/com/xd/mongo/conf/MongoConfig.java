package com.xd.mongo.conf;

import com.xd.mongo.convert.BigDecimalConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * mongo的配置信息
 *
 * @author xiaohei
 * @create 2019-12-13 下午1:45
 **/
@Configuration
public class MongoConfig {

    @Bean
    @ConditionalOnBean(EnableTransactionManagement.class)
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
    @Bean
    public MongoCustomConversions customConversions()
    {
        List<GenericConverter> converterList = new ArrayList<GenericConverter>();
        converterList.add(new BigDecimalConverter());
        return new MongoCustomConversions(converterList);
    }
}
