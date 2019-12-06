package com.xd.jpa;

import com.xd.jpa.util.SpringUtil;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Properties;

/**
 * 自定义分布式ID生成策略，兼容php，后期可以根据需要修改成雪花或者百度的分布式算法
 */
public class XdGenerationId implements Configurable, IdentifierGenerator {
    private String redisKey;
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        this.redisKey = properties.getProperty("key");
    }
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return getId();
    }

    public Integer getId() {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        if (!redisTemplate.hasKey(redisKey)) {
            redisTemplate.opsForValue().set(redisKey,20001);
        }
        return redisTemplate.opsForValue().increment(redisKey).intValue();
    }


}
