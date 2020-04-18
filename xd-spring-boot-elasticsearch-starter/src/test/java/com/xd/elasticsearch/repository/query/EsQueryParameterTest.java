package com.xd.elasticsearch.repository.query;

import com.xd.elasticsearch.repository.bean.SnsBlog;
import com.xd.elasticsearch.repository.bean.User;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

class EsQueryParameterTest {


    void testWhere() {
        EsQueryParameter parameter = new EsQueryParameter();
        parameter.eq(User::getId, 123);
        parameter.like(User::getNickname,"kk");
        parameter.limit(100);
        System.out.println(parameter.getWhereSql());
    }

    @Test
    public void testAnd(){
        EsQueryParameter parameter = new EsQueryParameter();
        parameter.or(i -> i.eq(User::getId, 1).and().like(User::getNickname,"123"));
        System.out.println(parameter.getWhereSql());
    }
    @Test
    public void testNestedAnd(){
        EsQueryParameter parameter = new EsQueryParameter();
        parameter.or(i -> i.eq(User::getId, 1).or(j->j.like(User::getNickname,"123").ne(User::getNickname,"123")));
        System.out.println(parameter.getWhereSql());
    }
    @Test
    public void testBlog(){
        EsQueryParameter parameter = new EsQueryParameter();
        parameter.and(i -> i.rightLike(SnsBlog::getNickname, "2324").or().rightLike(SnsBlog::getContent,"2324"))
        .and(i->i.eq(SnsBlog::getStatus,1).and().eq(SnsBlog::getType,1));
        System.out.println(parameter.getWhereSql());
    }

    @Test
    public  void testAndThen(){
        Consumer<Integer> consumer1 = x -> System.out.println("first x : " + x);
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second x : " + x);
        };
        Consumer<Integer> consumer3 = x -> System.out.println("third x : " + x);

        consumer1.andThen(consumer2).andThen(consumer3).accept(1);
    }



}