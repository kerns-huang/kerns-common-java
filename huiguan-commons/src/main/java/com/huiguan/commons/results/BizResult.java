/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.commons.results;

import com.google.common.base.Strings;

/**
 * 业务结果
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/17 下午1:39 $$
 */
public class BizResult<T> {
    /* 结果数据 */
    private T data;
    /* 状态码 */
    private StateCode stateCode;
    /* 状态描述辅助信息 */
    private String stateText;

    @java.beans.ConstructorProperties({"data", "stateCode", "stateText"})
    BizResult(T data, StateCode stateCode, String stateText) {
        this.data = data;
        if (null == stateCode) {
            throw new RuntimeException("stateCode must be not null");
        }
        this.stateCode = stateCode;
        this.stateText = stateText;
    }

    public static <T> BizResultBuilder<T> builder() {
        return new BizResultBuilder<T>();
    }

    public boolean isSuccess() {
        return this.stateCode.getCode() == 0;
    }

    public T getData() {
        return this.data;
    }

    public StateCode getStateCode() {
        return this.stateCode;
    }

    public String getStateText() {
        if (!Strings.isNullOrEmpty(stateText)) {
            return this.stateText;
        }
        return stateCode.getDesc();
    }

    public <DT> DubboResult<DT> toDubboResult(ObjectConvertFunction<T, DT> objectConvertFunction) {
        DT target = objectConvertFunction.convert(data);

        if (this.isSuccess()) {
            return DubboResults.newSuccessResult(target);
        }
        return DubboResults.newFailedResult(target, stateCode, getStateText());
    }


    public static class BizResultBuilder<T> {
        private T data;
        private StateCode stateCode;
        private String stateText;

        BizResultBuilder() {
        }

        public BizResult.BizResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public BizResult.BizResultBuilder<T> stateCode(StateCode stateCode) {
            this.stateCode = stateCode;
            return this;
        }

        public BizResult.BizResultBuilder<T> stateText(String stateText) {
            this.stateText = stateText;
            return this;
        }

        public BizResult<T> build() {
            return new BizResult<T>(data, stateCode, stateText);
        }

        public String toString() {
            return "com.huiguan.commons.results.BizResult.BizResultBuilder(data=" + this.data + ", stateCode=" + this.stateCode + ", stateText=" + this.stateText + ")";
        }
    }
}
