package io.kerns.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author xiaohei
 * @create 2020-06-23 下午4:07
 **/
@Configuration
@EnableConfigurationProperties(IdMarketProperties.class)
public class IdMarketAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public IdMarket idMarket(IdMarketProperties properties) {
        Assert.notNull(properties,"properties can not be null");
        IdMarket idMarket = new IdMarketImpl(properties.getWorkerId());
        IdMarkets.setIdMarket(idMarket);
        return idMarket;
    }

}
