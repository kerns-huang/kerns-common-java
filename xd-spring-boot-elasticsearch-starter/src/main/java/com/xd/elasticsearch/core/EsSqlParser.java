package com.xd.elasticsearch.core;

import com.xd.elasticsearch.repository.metadata.IndexInfo;
import com.xd.elasticsearch.repository.query.EsQueryParameter;

/**
 * es 的sql 解析器
 *
 * @author xiaohei
 * @create 2020-04-20 上午9:50
 **/
public class EsSqlParser {

    public static String parse(EsQueryParameter parameter, IndexInfo indexInfo){
        StringBuffer buffer=new StringBuffer();
        buffer.append(" select ");
        if(parameter.hasSelect()){
            buffer.append(parameter.getSelectSql());
        }else{
            buffer.append(indexInfo.getSelectSql());
        }
        buffer.append(" from ")
                .append(indexInfo.getIndexName());
        if(parameter.hashWhereCondition()){
            buffer.append(" where ").append(parameter.getWhereSql());
        }
        if(parameter.hasOrderCondition()){
            buffer.append(" order by ").append(parameter.getOrderSql());
        }
        if(parameter.hasLimit()){
            buffer.append(" limit ").append(parameter.getLimit());
        }
        String sql=buffer.toString().toLowerCase();
        return sql;
    }
}
