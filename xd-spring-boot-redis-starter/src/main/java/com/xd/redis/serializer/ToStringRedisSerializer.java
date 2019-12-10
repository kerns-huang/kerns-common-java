package com.xd.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * redis 序列化操，只为了适配php，后续可以移除掉
 */
public class ToStringRedisSerializer implements RedisSerializer<Object> {
    @Override
    public byte[] serialize(Object o) throws SerializationException {
         return (o == null ? null : o.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        String string = new String(bytes, StandardCharsets.UTF_8);
        return string;
    }
}
