package com.xd.cache;

import com.xd.annotations.cache.CacheMapKey;
import com.xd.core.lamda.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;
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

    @Resource(name = "toStringRedisTemplate")
    private RedisTemplate toStringRedisTemplate;

    /**
     * 设置key 和value
     *
     * @param key
     * @param value
     */
    public void setValue(String key, T value) {
        template.opsForValue().set(key, value);
    }

    /**
     * 设置值和过期时间
     *
     * @param key
     * @param value
     * @param timeOut 以秒为单位，过期时间
     */
    public void setValue(String key, T value, long timeOut) {
        template.opsForValue().set(key, value, Duration.ofSeconds(timeOut));
    }

    /**
     * 设置值和过期时间
     *
     * @param key
     * @param value
     * @param timeOut 以秒为单位，过期时间
     */
    public void setValue(String key, T value, long timeOut, TimeUnit timeUnit) {
        template.opsForValue().set(key, value, timeOut, timeUnit);
    }

    public T getValue(String key) {
        return template.opsForValue().get(key);
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
        Set<T> set = template.opsForZSet().reverseRange(key, start, end);
        return set.stream().collect(Collectors.toList());
    }

    public Set<T> zRangeByScore(String key, double minScore, double maxScore, long offset, long count) {
        return template.opsForZSet().rangeByScore(key, minScore, maxScore, offset, count);
    }

    /**
     * 添加value到set集合里面
     *
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

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return template.hasKey(key);
    }

    /**
     * 判断是否是set成员
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sIsMembers(String key, T value) {
        return template.opsForSet().isMember(key, value);
    }


    public void hMSet(String key, Map map) {
        template.opsForHash().putAll(key, map);
    }

    public <O> O hMget(String key, QueryWrapper<O> wrapper) {
        if (toStringRedisTemplate.hasKey(key)) {
                List<Object> keys = LamdaUtil.getCacheKeys(wrapper.getFunctions());
                List<String> values = toStringRedisTemplate.opsForHash().multiGet(key, keys);
                Map<String, Object> map = LamdaUtil.buildObj(values, wrapper.getO(), wrapper.getFunctions());
                BeanMap beanMap = BeanMap.create(wrapper.getO());
                beanMap.putAll(map);
                return wrapper.getO();
        } else {
            return null;
        }
    }

    public Long sRem(String key,T value){
       return  toStringRedisTemplate.opsForSet().remove(key,value);
    }

    public Boolean del(String key){
        return template.delete(key);
    }

    public Double hIncrByDouble(String key,String hashKey,double value){
        return toStringRedisTemplate.opsForHash().increment(key,hashKey,value);
    }

    public <O,L> Double hIncrByDouble(String key, SFunction<O,L> sFunction, double value){
        String hashKey= LamdaUtil.getCacheKey(sFunction);
        return toStringRedisTemplate.opsForHash().increment(key,hashKey,value);
    }

    public void hMSet(String key, UpdateWrapper wrapper) {
        toStringRedisTemplate.opsForHash().putAll(key, wrapper.getCacheMap());
    }

    public void expire(String key, Long timeOut) {
        template.expire(key, timeOut, TimeUnit.SECONDS);
    }

    public Boolean expireAt(String key, Date date) {
        return template.expireAt(key, date);
    }

    public Long incr(String key) {
        return template.opsForValue().increment(key);
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
            Set<ZSetOperations.TypedTuple<T>> set = template.opsForZSet().rangeWithScores(keys.get(0), 0, -1);
            template.opsForZSet().add(newkey, set);
            return Long.valueOf(set.size());
        } else {
            Set<String> set = new HashSet<>(keys.size() - 1);
            for (int i = 1; i < keys.size(); i++) {
                set.add(keys.get(i));
            }
            return template.opsForZSet().intersectAndStore(keys.get(0), set, newkey);
        }
    }


}
