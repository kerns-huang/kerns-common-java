/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.idmarket;

import java.io.Serializable;

/**
 * 业务类型枚举
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/8/2 上午10:50 $$
 */
public enum BusinessType implements Serializable {

    REPAST_ORDER(1L),//餐饮订单
    ;

    private static final long serialVersionUID = 1L;
    private long value;

    private BusinessType(long value) {
        this.value = value;
    }

    public long value() {
        return this.value;
    }
}
