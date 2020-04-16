package com.xd.elasticsearch.repository.metadata;

import lombok.Data;

/**
 * es index 相关的信息描述
 *
 * @author xiaohei
 * @create 2020-04-16 下午2:03
 **/
@Data
public class IndexInfo<T> {
    /**
     * index 对应的实体名字
     */
    private Class<T> entityType;
    /**
     * es 的文档名字
     */
    private String indexName;
}
