/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.global.lock;

import java.lang.annotation.*;

/**
 * 开启全局锁 starter注解
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 上午11:02 $$
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableGlobalLockConfiguration {
}
