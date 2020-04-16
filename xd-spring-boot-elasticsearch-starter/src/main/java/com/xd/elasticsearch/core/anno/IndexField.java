package com.xd.elasticsearch.core.anno;

public @interface IndexField {

    /**
     * el字段值,
     * 不需要配置该值的情况:
     */
    String value() default "";

    /**
     * 是否进行 select 查询
     * <p>大字段可设置为 false 不加入 select 查询范围</p>
     */
    boolean select() default true;
}
