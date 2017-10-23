/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.idmarket;

import com.google.common.base.Preconditions;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成器定义
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/8/2 上午10:54 $$
 */
public class IdMarketImpl implements IdMarket {


    private long workId;


    private ConcurrentHashMap<BusinessType, IdWorker> workerHolder = new ConcurrentHashMap<>();


    IdMarketImpl(long workId) {
        this.workId = workId;
    }

    public long nextId(BusinessType businessType) {
        Preconditions.checkArgument(null != businessType);
        if (workerHolder.containsKey(businessType)) {
            IdWorker idWorker=workerHolder.get(businessType);
            Preconditions.checkArgument(null != idWorker);
            return idWorker.nextId();
        }

        IdWorker idWorker = new SnowflakeIdWorker(workId, businessType.value());
        workerHolder.put(businessType, idWorker);
        return idWorker.nextId();
    }
}
