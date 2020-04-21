package com.xd.elasticsearch.repository.metadata;

import com.xd.core.lambda.PropertyNamer;
import com.xd.core.lambda.SFunction;
import com.xd.core.reflect.ReflectUtil;
import com.xd.core.util.StringUtils;
import com.xd.elasticsearch.core.anno.EsIndex;
import com.xd.elasticsearch.core.anno.EsIndexField;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 初始化索引
 *
 * @author xiaohei
 * @create 2020-04-16 下午2:44
 **/
public class IndexInfoHelper {
    /**
     * 储存反射类表信息
     */
    private static final Map<Class<?>, IndexInfo> INDEX_INFO_MAP = new ConcurrentHashMap<>();
    /**
     * lambda 函数和 列的对应关系
     */
    private static final Map<SFunction, String> FUNCTION_COLUMN_MAP = new ConcurrentHashMap<>();

    public static IndexInfo getIndexInfo(Class clazz) {
        if (!INDEX_INFO_MAP.containsKey(clazz)) {
            INDEX_INFO_MAP.put(clazz, initIndexInfo(clazz));
        }
        return INDEX_INFO_MAP.get(clazz);
    }

    public static String getColumn(SFunction fn) {
        try {
            if (FUNCTION_COLUMN_MAP.containsKey(fn)) {
                return FUNCTION_COLUMN_MAP.get(fn);
            }
            SerializedLambda serializedLambda = getSerializedLambda(fn);
            String getter = serializedLambda.getImplMethodName();
            Class cla = Class.forName(serializedLambda.getImplClass().replace("/", "."));
            String name = PropertyNamer.methodToProperty(getter);
            Field field = cla.getDeclaredField(name);
            EsIndexField indexField = field.getAnnotation(EsIndexField.class);
            String column = null;
            if (null == indexField || StringUtils.isBlank(indexField.value())) {
                column = field.getName();
                column = StringUtils.camelToUnderline(column);
                column = column.toLowerCase();
            } else {
                column = indexField.value();
            }
            FUNCTION_COLUMN_MAP.put(fn, column);
            return column;
        } catch (Exception var5) {
            throw new UnsupportedOperationException("method can not find cache key");
        }
    }

    private static <T> SerializedLambda getSerializedLambda(SFunction<T, ?> fn) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = fn.getClass().getDeclaredMethod("writeReplace");
        method.setAccessible(Boolean.TRUE);
        return (SerializedLambda) method.invoke(fn);
    }

    /**
     * 初始化 es index 和 实体类的对应关系
     *
     * @param clazz
     * @return
     */
    public static IndexInfo initIndexInfo(Class clazz) {
        if (INDEX_INFO_MAP.containsKey(clazz)) {
            return INDEX_INFO_MAP.get(clazz);
        }
        List<Field> fieldList = ReflectUtil.getField(clazz);
        List<IndexFieldInfo> fieldInfoList = fieldList.stream().map(a -> {
            return new IndexFieldInfo(a);
        }).collect(Collectors.toList());
        IndexInfo indexInfo = new IndexInfo(clazz, initIndexName(clazz), fieldInfoList);
        INDEX_INFO_MAP.put(clazz, indexInfo);
        return indexInfo;
    }


    private static String initIndexName(Class<?> clazz) {
        EsIndex esIndex = clazz.getAnnotation(EsIndex.class);
        String tableName;
        if (esIndex == null || StringUtils.isEmpty(esIndex.value())) {
            tableName = clazz.getSimpleName();
            // 开启表名下划线申明
            tableName = StringUtils.camelToUnderline(tableName);
            // 大写命名判断
            tableName = tableName.toLowerCase();
        } else {
            tableName = esIndex.value();
        }
        return tableName;

    }

}
