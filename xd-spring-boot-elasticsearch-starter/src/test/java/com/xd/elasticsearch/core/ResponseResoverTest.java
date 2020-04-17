package com.xd.elasticsearch.core;

import com.xd.elasticsearch.core.response.JSONResponseResolver;
import com.xd.elasticsearch.core.response.ResponseResover;
import com.xd.elasticsearch.repository.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ResponseResoverTest {

    public static void main(String[] args){
        ResponseResover responseResover= new JSONResponseResolver();
        List<User> list= responseResover.resover("{\"columns\":[{\"name\":\"id\",\"type\":\"keyword\"},{\"name\":\"nickname\",\"type\":\"keyword\"}],\"rows\":[[\"20002\",\"test008\"]]}", User.class);
        System.out.println(list.get(0).getId());

        ArrayList values= new ArrayList<>();
        values.add(1);
        values.add(2);
        Object value=values.stream().map(i -> String.format("%s", i)).collect(Collectors.joining(",", "(", ")"));
        System.out.println(value);
    }
}