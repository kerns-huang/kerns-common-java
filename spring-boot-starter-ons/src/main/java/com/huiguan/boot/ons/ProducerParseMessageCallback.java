/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import com.aliyun.openservices.ons.api.Message;
import com.huiguan.boot.ons.events.OnsEvent;

/**
 * 消息转化回调
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/22 下午3:28 $$
 */
public interface ProducerParseMessageCallback<T extends OnsEvent> {
    /**
     * event转化为message
     *
     * @param event event
     * @return message
     */
    Message parse(T event);
}
