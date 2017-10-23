/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.commons.results;

/**
 * 模型转换接口
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/17 下午1:51 $$
 */
public interface ObjectConvertFunction<O, T> {

    T convert(O o);
}
