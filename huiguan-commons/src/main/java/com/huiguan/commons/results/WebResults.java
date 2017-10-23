/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.commons.results;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * web返回结果工具类
 * data 需包含默认构造器
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/6 上午9:22 $$
 */
public final class WebResults {
    /**
     * 私有构造器
     */
    private WebResults() {
    }

    /**
     * 成功结果
     *
     * @param data 回传数据
     * @param <T>  需实现Serializable接口
     * @return 成功结果
     */
    public static <T> WebResult<T> newSuccessResult(T data) {
        WebResult<T> result = new WebResult<T>();
        result.setData(data);
        result.setCode(CommonStateCodes.SUCCESS.getCode());
        result.setDesc(CommonStateCodes.SUCCESS.getDesc());
        return result;
    }

    /**
     * 只包含失败状态码的失败结果
     *
     * @param failedCode 失败状态码
     * @param <T>        需实现Serializable接口
     * @return 没有数据回传的失败结果
     */
    public static <T> WebResult<T> newFailedResult(StateCode failedCode) {
        return newFailedResult(null, failedCode);
    }

    /**
     * 包含状态码及状态文本的失败结果
     *
     * @param failedCode 失败状态码
     * @param stateText  状态文本
     * @param <T>        需实现Serializable接口
     * @return 有数据回传的失败结果
     */
    public static <T> WebResult<T> newFailedResult(StateCode failedCode, String stateText) {
        return newFailedResult(null, failedCode, stateText);
    }

    /**
     * 包含失败状态码且有数据回传的失败结果
     *
     * @param data       回传数据
     * @param failedCode 失败状态码
     * @param <T>        需实现Serializable接口
     * @return 有数据回传的失败结果
     */
    public static <T> WebResult<T> newFailedResult(T data, StateCode failedCode) {
        return newFailedResult(data, failedCode, failedCode.getDesc());
    }

    /**
     * 包含失败状态码及状态文本描述且有数据回传的失败结果
     *
     * @param data       回传数据
     * @param failedCode 失败状态码
     * @param stateText  状态描述文本
     * @param <T>        需实现Serializable接口
     * @return 包含失败状态码及状态文本描述且有数据回传的失败结果
     */
    public static <T> WebResult<T> newFailedResult(T data, StateCode failedCode, String stateText) {
        checkNotNull(failedCode);
        //checkArgument(failedCode.getCode() < 0,"invalid code, for failed result, code must be negative integers");

        WebResult<T> result = new WebResult<T>();
        result.setData(data);
        result.setCode(failedCode.getCode());
        result.setDesc(stateText);
        return result;
    }

}
