package com.xd.amqp.config;

import com.xd.amqp.redis.RedisAmqpTemplate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class MqConfig {
    @Bean
    public AmqpTemplate amqpTemplate(RedisTemplate toStringRedisTemplate) {
        RedisAmqpTemplate redisAmqpTemplate = new RedisAmqpTemplate();
        redisAmqpTemplate.setRedisTemplate(toStringRedisTemplate);
        return redisAmqpTemplate;
    }
}
