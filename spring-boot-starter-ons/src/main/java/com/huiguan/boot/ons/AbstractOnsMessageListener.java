/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.fastjson.util.IOUtils;
import com.google.common.base.Strings;
import com.huiguan.boot.ons.events.OnsEvent;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.lang.reflect.ParameterizedType;

/**
 * 消息监听
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/1 下午5:35 $$
 */
@Slf4j
public abstract class AbstractOnsMessageListener<T extends OnsEvent> implements OnsEventListener {

    protected abstract Action recevie(T event);

    protected Action handleParseException(Exception parseException) {
        return Action.CommitMessage;
    }


    protected Action handleBizException(Exception bizException) {
        return Action.CommitMessage;
    }

    protected T parse(Message message) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String msgOrgString=new String(message.getBody(), IOUtils.UTF8);
        log.debug("pre parse msg["+msgOrgString+"]");
        T event = JSON.parseObject(msgOrgString, entityClass);
        return event;
    }


    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        String msgString = JSON.toJSONString(message);
        log.info("recevie msg:[" + msgString + "],body["+new String(message.getBody(), IOUtils.UTF8)+"]");
        Exception parseException = null;
        Exception bizException = null;
        Action action = null;
        T event = null;
        try {
            event = parse(message);
        } catch (Exception e) {
            parseException = e;
        }
        if (null != parseException) {
            log.warn("parse message [" + msgString + "] err...", parseException);
            return handleParseException(parseException);
        }

        try {
            action = recevie(event);
            return action;
        } catch (Exception e) {
            bizException = e;
        }
        if (null != bizException) {
            log.warn("handle message [" + msgString + "] err...", bizException);
            return handleBizException(bizException);
        }
        return action;

    }
}
