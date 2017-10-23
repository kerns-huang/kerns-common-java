/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.huiguan.boot.ons.events.OnsEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 生产者静态类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/19 上午9:51 $$
 */
@Slf4j
public class Producers {
    static ProducerBean producerBean;

    public static SendResult send(OnsEvent event) {
        return send(event, new DefaultProducerParseMessageCallback());
    }

    public static SendResult send(OnsEvent event, ProducerParseMessageCallback producerParseMessageCallback) {
        try {
            log.info("send msg [" + JSON.toJSONString(event) + "]");
            if (null == producerParseMessageCallback) {
                return producerBean.send(new DefaultProducerParseMessageCallback().parse(event));
            }
            return producerBean.send(producerParseMessageCallback.parse(event));

        } catch (Exception e) {
            log.warn("send msg [" + JSON.toJSONString(event) + "] err...");
            return null;
        }
    }

}
