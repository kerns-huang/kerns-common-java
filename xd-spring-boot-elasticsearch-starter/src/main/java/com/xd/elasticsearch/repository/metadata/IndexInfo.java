package com.xd.elasticsearch.repository.metadata;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * es index 相关的信息描述
 *
 * @author xiaohei
 * @create 2020-04-16 下午2:03
 **/
@Getter
public class IndexInfo<T> {
    /**
     * index 对应的实体名字
     */
    private final Class<T> entityType;
    /**
     * es 的文档名字
     */
    private final String indexName;
    /**
     * 对应的属性字段
     */
    private final List<IndexFieldInfo> fieldInfoList;

    private Map<String,IndexFieldInfo> columnFileMap=new HashMap<>();

    public IndexInfo(Class<T> entityType,String indexName,List<IndexFieldInfo> fieldInfoList){
        this.entityType=entityType;
        this.indexName=indexName;
        this.fieldInfoList=fieldInfoList;
        this.columnFileMap=this.fieldInfoList.stream().collect(Collectors.toMap(IndexFieldInfo::getColumn,a->a));
    }

    public String getSelectSql(){
       return fieldInfoList.stream().map(a->a.getColumn()).collect(Collectors.joining(","));
    }



}
