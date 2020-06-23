package io.kerns.mongo.conf;

import io.kerns.mongo.convert.BigDecimalConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

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
    /**
     * 单个mongo的情况下不支持事务的操作，所以事务相关的处理，必须是一个replication的模式，
     * 具体的搭建 资料 查看 http://www.warmjoke.com/2020/05/29/%E5%9F%BA%E4%BA%8Edocker%E6%90%AD%E5%BB%BAmongodb/
     * @param dbFactory
     * @return
     */
    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
    @Bean
    public MongoCustomConversions customConversions(){
        List<GenericConverter> converterList = new ArrayList<GenericConverter>();
        converterList.add(new BigDecimalConverter());
        return new MongoCustomConversions(converterList);
    }
}
