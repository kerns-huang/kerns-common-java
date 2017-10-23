/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import java.io.Serializable;

/**
 * 消息类别
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/19 上午9:39 $$
 */
public enum EventType implements Serializable {
    //ext part
    ORDER_CANCEL_REQ(MSG.ORDER_CANCEL_REQ, TOPIC.ORDER_REQ_TOPIC, MSG.ORDER_CANCEL_REQ),;


    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * rocketmq topic
     */
    private String topic;

    /**
     * rocketmq tag
     */
    private String tag;

    private EventType(String name, String topic, String tag) {

        this.name = name;
        this.topic = topic;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getTag() {
        return tag;
    }

    private class TOPIC {
        private static final String ORDER_REQ_TOPIC = "order_event";
    }

    private class MSG {
        private static final String ORDER_CANCEL_REQ = "ORDER_CANCEL_REQ";
    }
}
