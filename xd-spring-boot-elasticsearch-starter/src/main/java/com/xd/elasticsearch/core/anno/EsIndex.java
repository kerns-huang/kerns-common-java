package com.xd.elasticsearch.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * es文档和实体的对应关系
 *
 * @author xiaohei
 * @create 2020-04-20 下午1:50
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EsIndex {

    /**
     * 对应的 es index name ,如果名字一样或者符合驼峰规则，可以不写，
     *
     */
    String value() default "";
}
