/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.global.lock;

import java.util.concurrent.locks.Lock;

/**
 * 全局锁工厂
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 下午1:22 $$
 */
public interface GlobalLockFactory {

    /**
     * 创建锁
     *
     * @param globalLockType 全局锁类型(这里作收拢)
     * @return 锁实例
     */
    Lock create(GlobalLockType globalLockType);
}
