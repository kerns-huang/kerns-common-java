/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.commons.results;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;

/**
 * web返回结果
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/6 上午9:23 $$
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WebResult<T> implements Serializable {

    private static final long   serialVersionUID = -3887725517645851694L;

    private static final String DATA_KEY         = "data";
    private static final String CODE_KEY         = "code";
    private static final String DESC_KEY         = "desc";

    /* 结果数据 */
    private T                   data;
    /* 状态码 */
    private Long                code;
    /* 状态描述辅助信息 */
    private String              desc;
    /**
     * traceId
     */
    private String              requestId;

    public WebResult() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
        if (null == code) {
            return true;
        }
        return code == 0;
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    public Long getCode() {
        return code;
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     */
    public void setCode(Long code) {

        this.code = code;
    }

    /**
     * 获取状态文本
     *
     * @return 状态文本
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add(DATA_KEY, this.data).add(CODE_KEY, this.code)
            .add(DESC_KEY, this.desc).omitNullValues().toString();
    }
}
