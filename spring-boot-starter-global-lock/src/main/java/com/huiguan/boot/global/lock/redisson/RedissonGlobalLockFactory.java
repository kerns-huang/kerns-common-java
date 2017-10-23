/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.global.lock.redisson;

import com.huiguan.boot.global.lock.GlobalLockFactory;
import com.huiguan.boot.global.lock.GlobalLockType;
import com.huiguan.boot.global.lock.LockTypeException;
import org.redisson.api.RedissonClient;

import java.util.concurrent.locks.Lock;

/**
 * redisson 锁工厂
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 下午2:22 $$
 */
public class RedissonGlobalLockFactory implements GlobalLockFactory {

    private RedissonClient redissonClient;

    public RedissonGlobalLockFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Lock create(GlobalLockType globalLockType) {
        if (null == globalLockType) {
            throw new LockTypeException("globalLockType must be not null");
        }
        return (Lock) redissonClient.getFairLock(globalLockType.name() + globalLockType.getParameter());
    }
}
