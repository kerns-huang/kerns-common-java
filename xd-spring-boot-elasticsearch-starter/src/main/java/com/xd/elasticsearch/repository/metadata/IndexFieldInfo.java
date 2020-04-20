package com.xd.elasticsearch.repository.metadata;

import com.xd.core.util.StringUtils;
import com.xd.elasticsearch.core.anno.EsIndexField;
import lombok.Getter;

import java.lang.reflect.Field;

/**
 * 字段属性映射
 *
 * @author xiaohei
 * @create 2020-04-16 下午2:21
 **/
@Getter
public class IndexFieldInfo {
    /**
     * 字段名
     */
    private final String column;
    /**
     * 属性名
     */
    private final String property;

    public IndexFieldInfo(Field field) {
        this.property = field.getName();
        EsIndexField indexField = field.getAnnotation(EsIndexField.class);
        String column = null;
        if (null == indexField || StringUtils.isBlank(indexField.value())) {
            column = this.property;
            column = StringUtils.camelToUnderline(column);
            column = column.toUpperCase();
        } else {
            column = indexField.value();
        }
        this.column = column;

    }
}
