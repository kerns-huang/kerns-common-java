package com.xd.elasticsearch.repository.metadata;

import com.xd.core.reflect.ReflectUtil;
import com.xd.core.util.StringUtils;

import java.lang.reflect.Field;
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


    public static IndexInfo getIndexInfo(Class clazz){
        if(!INDEX_INFO_MAP.containsKey(clazz)) {
            INDEX_INFO_MAP.put(clazz,initIndexInfo(clazz));
        }
       return INDEX_INFO_MAP.get(clazz);
    }
    /**
     * 初始化 es index 和 实体类的对应关系
     * @param clazz
     * @return
     */
    public  static IndexInfo initIndexInfo(Class clazz) {
        if(INDEX_INFO_MAP.containsKey(clazz)){
            return INDEX_INFO_MAP.get(clazz);
        }
        List<Field> fieldList = ReflectUtil.getField(clazz);
        List<IndexFieldInfo> fieldInfoList = fieldList.stream().map(a -> {
            return new IndexFieldInfo(a);
        }).collect(Collectors.toList());
        IndexInfo indexInfo = new IndexInfo(clazz,initIndexName(clazz.getSimpleName()),fieldInfoList);
        INDEX_INFO_MAP.put(clazz,indexInfo);
        return indexInfo;
    }


    private static String initIndexName(String className) {
        String tableName = className;
        // 开启表名下划线申明
        tableName = StringUtils.camelToUnderline(tableName);
        // 大写命名判断
        tableName = tableName.toUpperCase();

        return tableName;
    }

}
