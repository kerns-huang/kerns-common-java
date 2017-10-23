/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ons属性
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/1 上午10:49 $$
 */
@ConfigurationProperties(prefix = "spring.ons")
public class OnsProperties {

    public static final String PROFILE_PROD = "prod";
    public static final String PROFILE_DEV = "dev";

    public static final String ACCESS_KEY = "LTAIlPHLJphRCGzu";
    public static final String SECRET_KEY = "Jop8TjinkjpqKtcl3sBGcWUDMKdvfn";

    //公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
    //公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
    public static final String ONS_ADDR_PROD = "http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal";
    public static final String ONS_ADDR_TEST = "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet";

    private String addr;

    private String producergroup;

    private String consumergroup;

    //private String messageModel;

    public String getAddr() {
        return this.addr;
    }

    public String getProducergroup() {
        return this.producergroup;
    }

    public String getConsumergroup() {
        return this.consumergroup;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setProducergroup(String producergroup) {
        this.producergroup = producergroup;
    }

    public void setConsumergroup(String consumergroup) {
        this.consumergroup = consumergroup;
    }

    //public String getMessageModel() {
        //return this.messageModel;
    //}

    //public void setMessageModel(String messageModel) {
        //this.messageModel = messageModel;
    //}
}
