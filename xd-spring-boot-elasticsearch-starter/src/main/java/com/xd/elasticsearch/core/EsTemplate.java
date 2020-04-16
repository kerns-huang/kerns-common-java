package com.xd.elasticsearch.core;

import com.xd.elasticsearch.ElasticSearchClient;
import com.xd.elasticsearch.repository.query.EsQueryParameter;
import com.xd.json.JSONUtils;

import java.util.List;

/**
 * es 操作模版
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:34
 **/
public class EsTemplate implements EsOperations {

    private ElasticSearchClient client;

    public EsTemplate(ElasticSearchClient client){
       this.client=client;
    }

    /**
     *
     * @param sql 绑定的sql
     * @param entityClass 传入的对象数值
     * @param resultClass 返回的对象
     * @param <T>
     * @return
     */
    public <T> List<T> findList(EsQueryParameter parameter, Class<T> resultClass){
        //TODO 处理sql 把入参的值和sql的变量进行绑定
        String response= client.post("");
        // 把json串转换成字符串对象
        return JSONUtils.toList(response,resultClass);
    }


}
