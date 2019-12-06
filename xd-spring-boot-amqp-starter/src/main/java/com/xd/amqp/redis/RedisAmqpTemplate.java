package com.xd.amqp.redis;

import lombok.Setter;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 临时解决方案，后期通过rabbitmq获取rocketmq替换该方案
 */
public class RedisAmqpTemplate implements AmqpTemplate {
    @Setter
    private RedisTemplate redisTemplate;

    /**
     * 通用的写法，目前只实现这个，后期可以通过rabbitmq实现这里的逻辑
     * 目前通过xadd实现
     * @param s
     * @param s1
     * @param o
     * @throws AmqpException
     */
    @Override
    public void convertAndSend(String s, String s1, Object o) throws AmqpException {
        Map<String,Object> map=obj2Map(o);
        Record record= StreamRecords.newRecord().in(s1).ofMap(map);
        redisTemplate.opsForStream().add(record);

    }

    public Map<String,Object> obj2Map(Object obj){
        if(obj instanceof Map){
            return (Map)obj;
        }
        try {
            Map<String, Object> map = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
            return map;
        }catch (Exception e){
            throw new AmqpException(e);
        }
    }


    @Override
    public void send(Message message) throws AmqpException {
      throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public void send(String s, Message message) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public void send(String s, String s1, Message message) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public void convertAndSend(Object o) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public void convertAndSend(String s, Object o) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }



    @Override
    public void convertAndSend(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public void convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public void convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Message receive() throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Message receive(String s) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Message receive(long l) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Message receive(String s, long l) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Object receiveAndConvert() throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Object receiveAndConvert(String s) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Object receiveAndConvert(long l) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public Object receiveAndConvert(String s, long l) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public <T> T receiveAndConvert(ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public <T> T receiveAndConvert(String s, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public <T> T receiveAndConvert(long l, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public <T> T receiveAndConvert(String s, long l, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s, String s1) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s1, String s2) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return false;
    }

    @Override
    public Message sendAndReceive(Message message) throws AmqpException {
        return null;
    }

    @Override
    public Message sendAndReceive(String s, Message message) throws AmqpException {
        return null;
    }

    @Override
    public Message sendAndReceive(String s, String s1, Message message) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(Object o) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, Object o) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        throw new UnsupportedOperationException("unsupport error");
    }


}
