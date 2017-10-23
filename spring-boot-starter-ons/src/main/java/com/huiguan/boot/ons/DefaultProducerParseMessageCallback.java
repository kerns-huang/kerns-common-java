/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.huiguan.boot.ons.events.OnsEvent;

/**
 * 消息转化回调
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/22 下午3:28 $$
 */
public class DefaultProducerParseMessageCallback implements ProducerParseMessageCallback {
    /**
     * event转化为message
     *
     * @param event event
     * @return message
     */
    public Message parse(OnsEvent event){
        Message message = new Message();
        message.setTopic(TopicPreHolder.getTopicPre() + event.getEventType().getTopic());
        message.setTag(event.getEventType().getTag());
        message.setBody(JSON.toJSONBytes(event));
        message.setKey(event.getMsgKey());
        return message;
    }
}
