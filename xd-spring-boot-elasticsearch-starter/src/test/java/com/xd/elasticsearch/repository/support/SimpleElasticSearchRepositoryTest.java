package com.xd.elasticsearch.repository.support;

import com.xd.elasticsearch.ElasticSearchClient;
import com.xd.elasticsearch.core.EsTemplate;
import com.xd.elasticsearch.core.ResponseResover;
import com.xd.elasticsearch.repository.bean.User;
import com.xd.elasticsearch.repository.metadata.IndexInfo;
import com.xd.elasticsearch.repository.metadata.IndexInfoHelper;
import com.xd.elasticsearch.repository.query.EsQueryParameter;

class SimpleElasticSearchRepositoryTest {


    public static void main(String[] args){
        IndexInfo<User> indexInfo=  IndexInfoHelper.initIndexInfo(User.class);
        //测试数据根据实际环境替换
        ElasticSearchClient client=new ElasticSearchClient("http://103.207.165.4:9200/_sql?format=json");
        ResponseResover responseResover=new ResponseResover();
        EsTemplate esTemplate=new EsTemplate(client,responseResover);
        SimpleElasticSearchRepository<User,Integer> repository=new SimpleElasticSearchRepository<>(indexInfo,esTemplate);
        EsQueryParameter esQueryParameter=new EsQueryParameter();
        esQueryParameter.like(User::getNickname,"啊");
        esQueryParameter.limit(1);
        repository.findList(esQueryParameter);
    }

}