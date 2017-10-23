/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.idmarket;

import com.google.common.base.Preconditions;

/**
 * 发号器工具类
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/8/2 上午11:10 $$
 */
public class IdMarkets {

    private static IdMarket idMarket;

    static void setIdMarket(IdMarket idMarket) {
        IdMarkets.idMarket = idMarket;
    }

    public static long nextId(BusinessType businessType) {
        Preconditions.checkArgument(null != businessType);
        Preconditions.checkArgument(null != idMarket);
        return idMarket.nextId(businessType);
    }
}
