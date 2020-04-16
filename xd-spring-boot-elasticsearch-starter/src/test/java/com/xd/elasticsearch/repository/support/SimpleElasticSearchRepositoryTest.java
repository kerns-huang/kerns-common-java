package com.xd.elasticsearch.repository.support;

import com.xd.elasticsearch.ElasticSearchClient;
import com.xd.elasticsearch.core.EsTemplate;
import com.xd.elasticsearch.repository.bean.User;
import com.xd.elasticsearch.repository.metadata.IndexInfo;
import com.xd.elasticsearch.repository.query.EsQueryParameter;
import org.junit.jupiter.api.Test;

class SimpleElasticSearchRepositoryTest {


    @Test
    void name() {
        IndexInfo<User> indexInfo=new IndexInfo<>();
        indexInfo.setEntityType(User.class);
        indexInfo.setIndexName("user");
        ElasticSearchClient client=new ElasticSearchClient("http://103.207.165.4:9200/_sql?format=json");
        EsTemplate esTemplate=new EsTemplate(client);
        SimpleElasticSearchRepository<User,Integer> repository=new SimpleElasticSearchRepository<>(indexInfo,esTemplate);
        EsQueryParameter esQueryParameter=new EsQueryParameter();
        esQueryParameter.like(User::getNickname,"啊");
        repository.findList(esQueryParameter);
    }
}