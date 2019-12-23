package com.xd.cache;

import com.xd.core.lambda.LambdaUtil;
import com.xd.core.lambda.QueryWrapper;
import com.xd.core.lambda.SFunction;
import com.xd.core.lambda.UpdateWrapper;
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
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, T> redisTemplate;

    @Resource(name = "toStringRedisTemplate")
    private RedisTemplate toStringRedisTemplate;

    /**
     * 设置key 和value
     *
     * @param key
     * @param value
     */
    public void setValue(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置值和过期时间
     *
     * @param key
     * @param value
     * @param timeOut 以秒为单位，过期时间
     */
    public void setValue(String key, T value, long timeOut) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(timeOut));
    }

    /**
     * 设置值和过期时间
     *
     * @param key
     * @param value
     * @param timeOut 以秒为单位，过期时间
     */
    public void setValue(String key, T value, long timeOut, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public T getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public <O> Long rPush(String key,O obj){
        return  toStringRedisTemplate.opsForList().rightPush(key,obj);
    }

    /**
     * 添加zset 数据
     *
     * @param key
     * @param score
     * @param value
     */
    public Boolean zAdd(String key, Double score, T value) {
        return toStringRedisTemplate.opsForZSet().add(key, value, score);
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
        Set<T> set = toStringRedisTemplate.opsForZSet().reverseRange(key, start, end);
        return set.stream().collect(Collectors.toList());
    }

    /**
     * 按照分数据获取数据
     *
     * @param key
     * @param minScore
     * @param maxScore
     * @param offset
     * @param count
     * @return
     */
    public Set<T> zRangeByScore(String key, double minScore, double maxScore, long offset, long count) {
        return toStringRedisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore, offset, count);
    }

    /**
     * 添加value到set集合里面
     *
     * @param key
     * @param values
     * @return
     */
    public Long sAdd(String key, T... values) {
        return toStringRedisTemplate.opsForSet().add(key, values);
    }

    /**
     * 是否是 set 里面的 成员
     *
     * @param key
     * @return
     */
    public Set<T> sMembers(String key) {
        return toStringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 获取一个set的长度
     * @param key
     * @return
     */
    public Long sSard(String key){
        return toStringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return toStringRedisTemplate.hasKey(key);
    }

    /**
     * 判断是否是set成员
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sIsMembers(String key, T value) {
        return toStringRedisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 设置hash 的 kev hashkey value
     */
    public void hMSet(String key, Map map) {
        toStringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取hash的多个字段，转换成cache对象
     *
     * @param key
     * @param wrapper
     * @param <O>
     * @return
     */
    public <O> O hMget(String key, QueryWrapper<O> wrapper) {
        if (toStringRedisTemplate.hasKey(key)) {
            if (wrapper.getFunctions() == null) {
                Map<Field, String> map1 = LambdaUtil.getClassFieldCacheKeyMap(wrapper.getO().getClass());
                Map<String, String> map2 = toStringRedisTemplate.opsForHash().entries(key);
                Map<String, Object> result = new HashMap<>();
                map1.entrySet().forEach(a -> {
                    if (map2.containsKey(a.getValue())) {
                        result.put(a.getKey().getName(), LambdaUtil.fillValue(a.getKey(), map2.get(a.getValue())));
                    }
                });
                BeanMap beanMap = BeanMap.create(wrapper.getO());
                beanMap.putAll(result);
                return wrapper.getO();

            } else {
                List<Object> keys = LambdaUtil.getCacheKeys(wrapper.getFunctions());
                List<String> values = toStringRedisTemplate.opsForHash().multiGet(key, keys);
                Map<String, Object> map = LambdaUtil.buildObj(values, wrapper.getO(), wrapper.getFunctions());
                BeanMap beanMap = BeanMap.create(wrapper.getO());
                beanMap.putAll(map);
                return wrapper.getO();
            }
        } else {
            return null;
        }
    }

    /**
     * 获取redis hash 的某个值
     *
     * @param key
     * @param hashKey
     * @return
     */
    public String hGet(String key, String hashKey) {
        return (String) toStringRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 添加或者覆盖 hash 的某个值
     *
     * @param key
     * @param function
     * @param value
     * @param <O>
     * @param <F>
     */
    public <O, F> void hSet(String key, SFunction<O, F> function, F value) {
        String hashKey = LambdaUtil.getCacheKey(function);
        toStringRedisTemplate.opsForHash().put(key, hashKey, value);
    }


    /**
     * 添加或者覆盖 hash 的某个值
     *
     * @param key
     * @param hashKey
     * @param value
     * @param <O>
     */
    public <O> void hSet(String key, String hashKey, O value) {
        toStringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取hash 的所有，后期观察下，数据量大的情况下的问题
     *
     * @param key
     * @return
     */
    public List<String> hVals(String key) {
        return toStringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 获取redis hash 的某个值,支持范型
     *
     * @param key
     * @param function
     * @return
     */
    public <O, F> F hGet(String key, SFunction<O, F> function) {
        Field field = LambdaUtil.getField(function);
        String hashKey = LambdaUtil.getCacheKey(function);
        if (!toStringRedisTemplate.opsForHash().hasKey(key, hashKey)) {
            return null;
        } else {
            String value = (String) toStringRedisTemplate.opsForHash().get(key, hashKey);
            return (F) LambdaUtil.fillValue(field, value);
        }
    }

    /**
     * 从 hash set 中删除一个元素
     *
     * @param key
     * @param value
     * @return
     */
    public Long sRem(String key, T value) {
        return toStringRedisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 从缓存中删除元素
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 对hash 里面的某个属性，进行属性操作
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public Double hIncrByDouble(String key, String hashKey, double value) {
        return toStringRedisTemplate.opsForHash().increment(key, hashKey, value);
    }

    /**
     * 对hash 里面的某个属性，进行属性操作
     *
     * @param key
     * @param sFunction
     * @param value
     * @return
     */
    public <O, L> Double hIncrByDouble(String key, SFunction<O, L> sFunction, double value) {
        String hashKey = LambdaUtil.getCacheKey(sFunction);
        return toStringRedisTemplate.opsForHash().increment(key, hashKey, value);
    }

    /**
     * 对hash 里面的多个属性进行更新操作
     *
     * @param key
     * @param sFunction
     * @param value
     * @return
     */
    public void hMSet(String key, UpdateWrapper wrapper) {
        toStringRedisTemplate.opsForHash().putAll(key, wrapper.getCacheMap());
    }

    /**
     * 删除 hash 的一个 key值
     * @param key
     * @param hashKey
     * @return
     */
    public Long hDel(String key,String hashKey){
       return toStringRedisTemplate.opsForHash().delete(key,hashKey);
    }

    /**
     * 设置时间超时
     *
     * @param key
     * @param timeOut
     */
    public void expire(String key, Long timeOut) {
        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 设置时间超时，在什么时候超时
     *
     * @param key
     * @param date
     */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 设置 自增
     *
     * @param key
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
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
            Set<ZSetOperations.TypedTuple<T>> set = toStringRedisTemplate.opsForZSet().rangeWithScores(keys.get(0), 0, -1);
            redisTemplate.opsForZSet().add(newkey, set);
            return Long.valueOf(set.size());
        } else {
            Set<String> set = new HashSet<>(keys.size() - 1);
            for (int i = 1; i < keys.size(); i++) {
                set.add(keys.get(i));
            }
            return toStringRedisTemplate.opsForZSet().intersectAndStore(keys.get(0), set, newkey);
        }
    }

    /**
     * 添加zset 某个对象的分值
     * @param key
     * @param score
     * @param value
     * @param <O>
     * @return
     */
    public <O> Double zIncrby(String key,double score,O value){
       return toStringRedisTemplate.opsForZSet().incrementScore(key,value,score);
    }


}
