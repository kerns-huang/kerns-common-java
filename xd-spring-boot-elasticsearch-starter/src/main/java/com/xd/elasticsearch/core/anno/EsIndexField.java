package com.xd.elasticsearch.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EsIndexField {

    /**
     * 不需要配置该值的情况:
     */
    String value() default "";

    /**
     * 是否进行 select 查询
     * <p>大字段可设置为 false 不加入 select 查询范围</p>
     */
    boolean select() default true;
}
