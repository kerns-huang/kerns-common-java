package com.xd.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 緩存適配器。當前使用的是SpringDate的RedisTemplate
 * 在springboot啟動項目中，請配置如下：
 * spring.redis.database=0
 * spring.redis.password=123
 * spring.redis.port=6379
 * spring.redis.host=192.168.5.14
 * spring.redis.lettuce.pool.min-idle=5
 * spring.redis.lettuce.pool.max-idle=10
 * spring.redis.lettuce.pool.max-active=8
 * spring.redis.lettuce.pool.max-wait=1ms
 * spring.redis.lettuce.shutdown-timeout=100ms
 */
@Component
public class CacheAdapter {
    @Autowired
    private RedisTemplate<String, String> template;

    public void setValue(String key,String value) {
        template.opsForValue().set(key,value);
    }
    public void setValue(String key,String value, long timeOut) {
        template.opsForValue().set(key,value,timeOut);
    }
    public void setValue(String key, String value, long timeOut, TimeUnit timeUnit) {
        template.opsForValue().set(key,value,timeOut,timeUnit );
    }
    public void getValue(String key){
        template.opsForValue().get(key);
    }
//    public void setSet(String key,String... values){
//        template.opsForSet().add(key,values);
//    }
//    public void getSet(String key){
//        template.opsForSet().difference(key,key);
//    }
}
