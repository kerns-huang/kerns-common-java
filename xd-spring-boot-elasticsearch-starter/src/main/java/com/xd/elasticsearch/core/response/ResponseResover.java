package com.xd.elasticsearch.core.response;

import java.util.List;

/**
 * 返回的json串转换成对象
 *
 * @author xiaohei
 * @create 2020-04-16 下午5:43
 **/
public interface ResponseResover {

     <T> List<T> resover( String response,Class<T> resultClass) ;
}
