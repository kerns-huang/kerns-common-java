/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.xd.elasticsearch.repository.query;

import com.xd.core.lambda.SFunction;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * 查询条件封装
 * <p>比较值</p>
 *
 * @author hubin miemie HCL
 * @since 2017-05-26
 */
public interface Compare extends Serializable {

    /**
     * 相等条件判断
     * @param filed
     * @param value
     * @param <O>
     * @param <F>
     * @return
     */
    <O, F> Compare eq(SFunction<O, F> filed, Object value);
    /**
     * in 条件判断
     * @param filed
     * @param value
     * @param <O>
     * @param <F>
     * @return
     */
    <O, F> Compare in(SFunction<O, F> filed, Collection<?> values);

    <O, F> Compare ne(SFunction<O, F> filed, Object val);

    <O, F> Compare gt(SFunction<O, F> filed, Object val);

    <O, F> Compare ge(SFunction<O, F> filed, Object val);

    <O, F> Compare lt(SFunction<O, F> filed, Object val);

    <O, F> Compare le(SFunction<O, F> filed, Object val);

    <O, F> Compare leftLike(SFunction<O, F> filed, String value);

    <O, F> Compare rightLike(SFunction<O, F> filed, String value);

}
