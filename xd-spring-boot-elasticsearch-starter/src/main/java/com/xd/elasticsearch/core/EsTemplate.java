package com.xd.elasticsearch.core;

import com.xd.elasticsearch.ElasticSearchClient;
import com.xd.elasticsearch.core.response.ResponseResover;
import com.xd.elasticsearch.repository.metadata.IndexInfo;
import com.xd.elasticsearch.repository.metadata.IndexInfoHelper;
import com.xd.elasticsearch.repository.query.EsQueryParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * es 操作模版
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:34
 **/
public class EsTemplate implements EsOperations {

    private ElasticSearchClient client;

    private ResponseResover responseResover;

    public EsTemplate(ElasticSearchClient client,ResponseResover responseResover){
       this.client=client;
       this.responseResover=responseResover;
    }

    /**
     *
     * @param parameter
     * @param resultClass
     * @param <T>
     * @return
     */
    public <T> List<T> findList(EsQueryParameter parameter, Class<T> resultClass){
        //TODO 处理sql 把入参的值和sql的变量进行绑定
        IndexInfo indexInfo= IndexInfoHelper.getIndexInfo(resultClass);
        StringBuffer buffer=new StringBuffer();
        buffer.append(" select ");
        if(parameter.hasSelect()){
            buffer.append(parameter.getSelectSql());
        }else{
            buffer.append(indexInfo.getSelectSql());
        }
        buffer.append(" from ")
                .append(indexInfo.getIndexName());
        if(parameter.hashWhereCondition()){
            buffer.append(" where ").append(parameter.getWhereSql());
        }
        if(parameter.hasOrderCondition()){
            buffer.append(" order by ").append(parameter.getOrderSql());
        }
        if(parameter.hasLimit()){
            buffer.append(" limit ").append(parameter.getLimit());
        }
        String sql=buffer.toString().toLowerCase();
        String response= client.post(sql);
        if(response==null){
            return new ArrayList<>();
        }
        // 把json串转换成字符串对象
        return responseResover.resover(response,resultClass);
    }


}
