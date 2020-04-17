package com.xd.elasticsearch.repository.query;

import com.xd.elasticsearch.repository.bean.User;
import org.junit.jupiter.api.Test;

class EsQueryParameterTest {

    @Test
    void name() {
        EsQueryParameter parameter = new EsQueryParameter();
        parameter.eq(User::getId, 123);
        parameter.like(User::getNickname,"kk");
        parameter.limit(100);
        System.out.println(parameter.getWhereSql());
    }



}