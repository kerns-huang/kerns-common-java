package com.xd.elasticsearch.repository.support;

import com.xd.elasticsearch.core.EsOperations;
import com.xd.elasticsearch.repository.metadata.IndexInfo;
import com.xd.elasticsearch.repository.query.EsQueryParameter;
import java.util.List;

/**
 * @author xiaohei
 * @create 2020-04-15 下午2:39
 **/
public class SimpleElasticSearchRepository<T, ID>    {

    private  IndexInfo indexInfo;

    private EsOperations esOperations;

    public SimpleElasticSearchRepository(IndexInfo indexInfo, EsOperations operations){
        this.indexInfo=indexInfo;
        this.esOperations=operations;
    }

    /**
     * 获取一行数据
     */
    public List<T>  findList(EsQueryParameter parameter) {
        return esOperations.findList(parameter,indexInfo.getEntityType());
    }
}
