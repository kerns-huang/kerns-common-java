package com.xd.cache;

import com.xd.core.lamda.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author watt
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
public class CacheAdapter<T> {
    @Autowired
    private RedisTemplate<String, T> template;

    /**
     * 设置key 和value
     * @param key
     * @param value
     */
    public void setValue(String key, T value) {
        template.opsForValue().set(key, value);
    }

    /**
     * 设置值和过期时间
     * @param key
     * @param value
     * @param timeOut 以秒为单位，过期时间
     */
    public void setValue(String key, T value, long timeOut) {
        template.opsForValue().set(key, value, Duration.ofSeconds(timeOut));
    }
    /**
     * 设置值和过期时间
     * @param key
     * @param value
     * @param timeOut 以秒为单位，过期时间
     */
    public void setValue(String key, T value, long timeOut, TimeUnit timeUnit) {
        template.opsForValue().set(key, value, timeOut, timeUnit);
    }

    public T getValue(String key) {
        template.opsForValue().get(key);
    }

    /**
     * 添加zset 数据
     *
     * @param key
     * @param score
     * @param value
     */
    public Boolean zAdd(String key, Double score, T value) {
        return template.opsForZSet().add(key, value, score);
    }

    /**
     * 按照分数获取数据
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<T> zRevRange(String key, Long start, Long end) {
        Set<ZSetOperations.TypedTuple<T>> set = template.opsForZSet().rangeWithScores(key, start, end);
        return set.stream().map(a -> a.getValue()).collect(Collectors.toList());
    }

    /**
     * 添加value到set集合里面
     * @param key
     * @param values
     * @return
     */
    public Long sAdd(String key, T... values) {
        return template.opsForSet().add(key, values);
    }


    public Set<T> sMembers(String key) {
        return template.opsForSet().members(key);
    }


    public void hMSet(String key,Map map){
        template.opsForHash().putAll(key,map);
    }

    public void update(String key, UpdateWrapper wrapper){
        template.opsForHash().putAll(key,wrapper.getCacheMap());
    }

    public void expire(String key,Long timeOut){
        template.expire(key,timeOut,TimeUnit.SECONDS);
    }
    /**
     * 获取key的交集，生成新的集合
     *
     * @param newkey
     * @param keys
     * @return
     */
    public Long zInterstore(String newkey, List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return 0L;
        }
        if (keys.size() == 1) {
            Set<ZSetOperations.TypedTuple<T>> set= template.opsForZSet().rangeWithScores(keys.get(0), 0, -1);
            template.opsForZSet().add(newkey, set);
            return Long.valueOf(set.size());
        } else {
            Set<String> set=new HashSet<>(keys.size()-1);
            for(int i=1;i<keys.size();i++){
                set.add(keys.get(i));
            }
            return template.opsForZSet().intersectAndStore(keys.get(0), set, newkey);
        }
    }


}
