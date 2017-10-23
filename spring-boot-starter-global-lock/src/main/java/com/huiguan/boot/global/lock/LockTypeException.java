/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.global.lock;

/**
 * 全局锁类别异常(不存在)
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 下午3:10 $$
 */
public class LockTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LockTypeException(Throwable throwable) {
        super(throwable);
    }

    public LockTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LockTypeException(String message) {
        super(message);
    }
}
