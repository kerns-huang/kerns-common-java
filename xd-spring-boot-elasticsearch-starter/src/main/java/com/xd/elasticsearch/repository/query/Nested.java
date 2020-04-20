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

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * 嵌套查询条件封装
 * <p>嵌套</p>
 */
public interface Nested<F> extends Serializable {


    /**
     * AND 嵌套
     * <p>
     * 例: and(i -&gt; i.eq("name", "李白").ne("status", "活着"))
     * </p>
     *
     */
   F and( Consumer<F> consumer);

    /**
     * OR 嵌套
     * <p>
     * 例: or(i -&gt; i.eq("name", "李白").ne("status", "活着"))
     *
     * </p>
     * or (12313 and 123123 ) or
     * @param condition 执行条件
     * @param consumer  消费函数
     * @return children
     */
    F  or(Consumer<F> consumer);

    /**
     * 正常嵌套 不带 AND 或者 OR,只使用 （）
     * <p>
     * 例: nested(i -&gt; i.eq("name", "李白").ne("status", "活着"))
     * </p>
     *
     * @param condition 执行条件
     * @param consumer  消费函数
     * @return children
     */
     F nested(Consumer<F> consumer);
}
