package com.xd.elasticsearch.core;

import com.xd.elasticsearch.repository.bean.User;
import com.xd.elasticsearch.repository.metadata.IndexInfo;
import com.xd.elasticsearch.repository.metadata.IndexInfoHelper;
import com.xd.elasticsearch.repository.query.EsQueryParameter;
import org.junit.jupiter.api.Test;

class EsSqlParserTest {

    @Test
    public void testSelect(){
       IndexInfo indexInfo= IndexInfoHelper.initIndexInfo(User.class);
        EsQueryParameter parameter=new EsQueryParameter();
        parameter.select(User::getId,User::getNickname);
        parameter.ge(User::getId,1)
                .eq(User::getNickname,"a");
        System.out.println(EsSqlParser.parse(parameter,indexInfo));
    }
    @Test
    public void testNestAnd(){
        IndexInfo indexInfo= IndexInfoHelper.initIndexInfo(User.class);
        EsQueryParameter parameter=new EsQueryParameter();
        parameter.or(i->i.like(User::getNickname,"a").or().like(User::getCountry,"a"));
        System.out.println(EsSqlParser.parse(parameter,indexInfo));
    }
    @Test
    public void testNested(){
        IndexInfo indexInfo=IndexInfoHelper.initIndexInfo(User.class);
        EsQueryParameter parameter=new EsQueryParameter();
        parameter.nested(i->i.like(User::getNickname,"a").or().like(User::getCountry,"a"));
        System.out.println(EsSqlParser.parse(parameter,indexInfo));
    }



}