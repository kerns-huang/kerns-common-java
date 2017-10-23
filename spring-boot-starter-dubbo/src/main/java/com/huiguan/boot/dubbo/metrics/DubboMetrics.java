/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.dubbo.metrics;

import com.huiguan.boot.dubbo.domain.ClassIdBean;
import com.huiguan.boot.dubbo.listener.StaticsFilterHelper;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * dubbo监控
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
@Component
public class DubboMetrics implements PublicMetrics {

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new LinkedList<Metric<?>>();
        for (Map.Entry<ClassIdBean, Map<String, AtomicLong>> entry : StaticsFilterHelper.STATICS_DATA_MAP
                .entrySet()) {
            ClassIdBean classIdBean = entry.getKey();
            Map<String, AtomicLong> countMap = entry.getValue();
            for (Map.Entry<String, AtomicLong> entry1 : countMap.entrySet()) {
                metrics.add(new Metric<Number>("dubbo." + classIdBean + "." + entry1.getKey(),
                        entry1.getValue().get()));
            }
        }
        /*
        for (Map.Entry<ClassIdBean, Map<String, AtomicLong>> entry : StaticsFilterHelper.STATICS_DATA_MAP
                .entrySet()) {
            ClassIdBean classIdBean = entry.getKey();
            Map<String, AtomicLong> countMap = entry.getValue();
            for (Map.Entry<String, AtomicLong> entry1 : countMap.entrySet()) {
                metrics.add(new Metric<Number>("dubbo." + classIdBean + "." + entry1.getKey(),
                        entry1.getValue().get()));
            }
        }*/
        return metrics;
    }
}
