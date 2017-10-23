/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */


package com.huiguan.boot.ons;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * ons自动配置类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/1 上午11:02 $$
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(OnsProperties.class)
public class OnsAutoConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private OnsProperties onsProperties;


    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private List<OnsEventListener> onsMessageListeners;


    @Bean
    @ConditionalOnMissingBean
    public ProducerBean genProducerBean() {

        TopicPreHolder.setTopicPre(fetchTopicPre());

        String addr = fetchOnsAddr(onsProperties.getAddr());
        Preconditions.checkArgument(!Strings.isNullOrEmpty(addr), "spring.ons.addr must be not null");
        String producergroup = fetchOnsProducerGroup(onsProperties.getProducergroup());
        Preconditions.checkArgument(!Strings.isNullOrEmpty(producergroup), "spring.ons.producergroup must be not null");

        Properties producerProperties = new Properties();
        producerProperties.setProperty(PropertyKeyConst.ONSAddr, addr);
        producerProperties.setProperty(PropertyKeyConst.ProducerId, producergroup);
        producerProperties.setProperty(PropertyKeyConst.AccessKey, OnsProperties.ACCESS_KEY);
        producerProperties.setProperty(PropertyKeyConst.SecretKey, OnsProperties.SECRET_KEY);

        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(producerProperties);
        producerBean.start();
        Producers.producerBean = producerBean;
        return producerBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsumerBean genConsumerBean() {

        Map<Subscription, MessageListener> subscriptionTable = genListenerMap();
        if (CollectionUtils.isEmpty(subscriptionTable)) {
            return new ConsumerBean();
        }

        TopicPreHolder.setTopicPre(fetchTopicPre());
        String addr = fetchOnsAddr(onsProperties.getAddr());
        String consumergroup = fetchOnsConsumerGroup(onsProperties.getConsumergroup());
        Preconditions.checkArgument(!Strings.isNullOrEmpty(consumergroup), "spring.ons.consumergroup must be not null");

        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.ONSAddr, addr);
        consumerProperties.setProperty(PropertyKeyConst.ConsumerId, consumergroup);
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, OnsProperties.ACCESS_KEY);
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, OnsProperties.SECRET_KEY);

        //consumerProperties.setProperty(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
        //Map<Subscription, MessageListener> subscriptionTable = genListenerMap();

        ConsumerBean consumerBean = new ConsumerBean();

        consumerBean.setProperties(consumerProperties);
        consumerBean.setSubscriptionTable(subscriptionTable);
        consumerBean.start();
        return consumerBean;
    }

    private String fetchOnsAddr(String orginAddr) {
        if (!Strings.isNullOrEmpty(orginAddr)) {
            return orginAddr;
        }
        String[] profiles = environment.getActiveProfiles();

        if (null == profiles || profiles.length < 1) {
            return OnsProperties.ONS_ADDR_TEST;
        }
        for (String profileName : profiles) {
            if (OnsProperties.PROFILE_PROD.equals(profileName) || OnsProperties.PROFILE_DEV.equals(profileName)) {
                return OnsProperties.ONS_ADDR_PROD;
            }
        }
        return OnsProperties.ONS_ADDR_TEST;
    }

    private String fetchTopicPre() {

        String[] profiles = environment.getActiveProfiles();

        if (null == profiles || profiles.length < 1) {
            return "test_";
        }
        for (String profileName : profiles) {
            if (OnsProperties.PROFILE_PROD.equals(profileName)) {
                return "prod_";
            }
        }
        return "test_";
    }

    private String fetchOnsProducerGroup(String orginProducerGroup) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(orginProducerGroup), "producergroup must be not null");
        return orginProducerGroup;
    }

    private String fetchOnsConsumerGroup(String orginConsumerGroup) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(orginConsumerGroup), "consumergroup must be not null");
        return orginConsumerGroup;
    }

    private String fetchOnsConsumerModel(String consumerModel) {
        if (Strings.isNullOrEmpty(consumerModel)) {
            return PropertyValueConst.CLUSTERING;
        }
        if (PropertyValueConst.CLUSTERING.equals(consumerModel) && PropertyValueConst.BROADCASTING.equals(consumerModel)) {
            return PropertyValueConst.CLUSTERING;
        }
        return PropertyValueConst.CLUSTERING;
    }

    private Map<Subscription, MessageListener> genListenerMap() {
        if (CollectionUtils.isEmpty(onsMessageListeners)) {
            return Maps.newHashMap();
        }
        Map<Subscription, MessageListener> messageListenerMap = Maps.newConcurrentMap();
        for (OnsEventListener messageListener : onsMessageListeners) {
            Preconditions.checkArgument(null != messageListener.eventType(), "messageListener.eventType must be not null");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(messageListener.eventType().getTopic()), "messageListener.eventType.topic must be not null");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(messageListener.eventType().getTag()), "messageListener.eventType.tagExpression must be not null");
            Subscription subscription = new Subscription();
            subscription.setTopic(TopicPreHolder.getTopicPre() + messageListener.eventType().getTopic());
            subscription.setExpression(messageListener.eventType().getTag());
            messageListenerMap.put(subscription, messageListener);
            log.info("register listener [clazz:" + messageListener.getClass() + "@" + JSON.toJSONString(messageListener.eventType()) + "],subscription[" + JSON.toJSONString(subscription) + "]");
        }

        return messageListenerMap;
    }

}
