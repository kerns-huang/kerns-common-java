/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.commons.results;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

/**
 * Dubbo返回结果
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/6 上午9:23 $$
 * @deprecated 将被DubboResult替换,请尽快升级
 */
public class Result<T> implements Serializable {

    private static final long   serialVersionUID = -3887725517645851694L;

    private static final String DATA_KEY         = "data";
    private static final String STATE_CODE_KEY   = "stateCode";
    private static final String STATE_TEXT_KEY   = "stateText";
    /* 结果数据 */
    private T         data;
    /* 状态码 */
    private StateCode stateCode;
    /* 状态描述辅助信息 */
    private String    stateText;
    public Result() {
    }

    /**
     * 获取回传数据
     *
     * @return 回传数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置回传数据
     *
     * @param data 回传数据
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取成功结果
     *
     * @return 是成功吗
     */
    public boolean isSuccess() {
        return stateCode.getCode() == 0;
    }

    /**
     * 获取失败结果
     *
     * @return 是失败吗
     */
    public boolean isFailed() {
        return !isSuccess();
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    public StateCode getStateCode() {
        return stateCode;
    }

    /**
     * 设置状态码
     *
     * @param stateCode 状态码
     */
    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * 获取状态文本
     *
     * @return 状态文本
     */
    public String getStateText() {
        return stateText;
    }

    /**
     * 设置状态文本
     *
     * @param statusText 状态文本
     */
    public void setStateText(String statusText) {
        this.stateText = statusText;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add(DATA_KEY, this.data)
            .add(STATE_CODE_KEY, this.stateCode).add(STATE_TEXT_KEY, this.stateText)
            .omitNullValues().toString();
    }
}
