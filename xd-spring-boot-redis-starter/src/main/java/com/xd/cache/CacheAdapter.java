package com.xd.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private RedisTemplate<String, Object> template;
    public void setValue(String key,Object value) {
        template.opsForValue().set(key,value);
    }
    public void setValue(String key,Object value, long timeOut) {
        template.opsForValue().set(key,value,timeOut);
    }
    public void setValue(String key, Object value, long timeOut, TimeUnit timeUnit) {
        template.opsForValue().set(key,value,timeOut,timeUnit );
    }
    public void getValue(String key){
        template.opsForValue().get(key);
    }

    /**
     * 添加zset 数据
     * @param key
     * @param score
     * @param value
     */
    public void zAdd(String key,Double score,Object value){
        template.opsForZSet().add(key,value,score);
    }

    /**
     * 按照分数获取数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> zRevRange(String key,Long start,Long end){
        Set<ZSetOperations.TypedTuple<Object>> set= template.opsForZSet().rangeWithScores(key,start,end);
        return set.stream().map(a->a.getValue()).collect(Collectors.toList());
    }



}
