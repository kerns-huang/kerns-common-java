/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import com.aliyun.openservices.ons.api.MessageListener;

/**
 * 消息监听
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/1 下午5:35 $$
 */
public interface OnsEventListener extends MessageListener {


    /**
     * 消息类别
     *
     * @return 消息类别
     */
    EventType eventType();
}
