/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons.events;

import com.huiguan.boot.ons.EventType;

import java.io.Serializable;

/**
 * 请求事件接口
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/19 上午9:54 $$
 */
public interface OnsEvent extends Serializable {
    /**
     * 获取消息类别
     *
     * @return 消息类别
     */
    EventType getEventType();

    /**
     * 消息标识
     *
     * @return 消息标识
     */
    String getMsgKey();

}
