/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.global.lock;

import java.io.Serializable;

/**
 * 全局锁枚举
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 下午3:05 $$
 */
public enum GlobalLockType implements Serializable {

    TMP_TEST("TMP_TEST"),
    REPEAST_CANCLE_BESPEAK("REPEAST_CANCLE_BESPEAK"),
    REPEAST_CONFIRM_REPAST_LOCATIONKEY("REPEAST_CONFIRM_REPAST_LOCATIONKEY"),
    REPEAST_GET_ORDER_DISHES("REPEAST_GET_ORDER_DISHES"),
    REPEAST_SUBTRACT_ITEM("REPEAST_SUBTRACT_ITEM"),
    REPEAST_ADD_ITEM("REPEAST_ADD_ITEM"),
    REPEAST("REPEAST"),
    REPEAST_SUBMIT("REPEAST_SUBMIT"),
    REPEAST_CONFIRM("REPEAST_CONFIRM"),
    REPEAST_CONFIRM_ITEM("REPEAST_CONFIRM_ITEM"),
    REPEAST_CHANGE_ITEM_AFTER_SUBMIT("REPEAST_CHANGE_ITEM_AFTER_SUBMIT"),
    WECHANT_LOGIN("WECHANT_LOGIN"),

    REPAST_LOCATION_KEY("REPAST_LOCATION_KEY"),
    MERCHANT_BALANCE("MERCHANT_BALANCE"),
    ACCOUNT_BALANCE("ACCOUNT_BALANCE"),
    ACCOUNT_POINT("ACCOUNT_POINT");

    private static final long serialVersionUID = 1L;
    private String name;

    private String parameter;

    private GlobalLockType(String name) {
        this.name = name;
    }

    public GlobalLockType setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getParameter() {
        return null == parameter ? "" : parameter;
    }
}
