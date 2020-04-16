package com.xd.elasticsearch.core;

import com.xd.elasticsearch.repository.query.EsQueryParameter;

import java.util.List;

/**
 * 可以对es的操作
 *
 * @author xiaohei
 * @create 2020-04-15 上午11:33
 **/
public interface EsOperations {


    <T> List<T> findList(EsQueryParameter parameter, Class<T> resultClass);
}
