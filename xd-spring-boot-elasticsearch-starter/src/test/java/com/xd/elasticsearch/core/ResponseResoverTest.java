package com.xd.elasticsearch.core;

import com.xd.elasticsearch.repository.bean.User;

import java.util.List;

class ResponseResoverTest {

    public static void main(String[] args){
        ResponseResover responseResover= new ResponseResover();
        List<User> list= responseResover.resover("{\"columns\":[{\"name\":\"id\",\"type\":\"keyword\"},{\"name\":\"nickname\",\"type\":\"keyword\"}],\"rows\":[[\"20002\",\"test008\"]]}", User.class);
        System.out.println(list.get(0).getId());
    }
}