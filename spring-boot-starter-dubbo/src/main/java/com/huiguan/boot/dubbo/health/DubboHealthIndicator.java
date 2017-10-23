/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.dubbo.health;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.huiguan.boot.dubbo.DubboConsumerAutoConfiguration;
import com.huiguan.boot.dubbo.domain.ClassIdBean;
import com.huiguan.boot.dubbo.listener.ConsumerSubscribeListener;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * dubbo 健康监控
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
@Component
public class DubboHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        for (ClassIdBean classIdBean : ConsumerSubscribeListener.SUBSCRIBEDINTERFACES_SET) {
            Object service = DubboConsumerAutoConfiguration.DUBBO_REFERENCES_MAP.get(classIdBean);
            EchoService echoService = (EchoService) service;
            if (echoService != null) {
                echoService.$echo("Hello");
                builder.withDetail(classIdBean.toString(), true);
            }
        }
        builder.up();
    }

}
