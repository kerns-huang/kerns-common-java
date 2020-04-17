package com.xd.elasticsearch.repository.query;

import com.xd.core.lambda.LambdaUtil;
import com.xd.core.lambda.SFunction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * es 查询需要传入的参数
 *
 * @author xiaohei
 * @create 2020-04-15 下午6:47
 **/
public class EsQueryParameter {
    /**
     * 具体传入的参数，如果是object 对象，做一层转换
     */
    private final List<String> whereCondition = new ArrayList<>();
    @Getter
    private int limit = -1;

    public <O, F> EsQueryParameter eq(SFunction<O, F> filed, Object value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " = " + value);
        return this;
    }


    public <O, F> EsQueryParameter ne(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " <= " + val);
        return this;
    }

    public <O, F> EsQueryParameter gt(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " > " + val);
        return this;
    }

    public <O, F> EsQueryParameter ge(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " >= " + val);
        return this;
    }

    public <O, F> EsQueryParameter lt(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " < " + val);
        return this;
    }

    public <O, F> EsQueryParameter le(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " <= " + val);
        return this;
    }

    public <O, F> EsQueryParameter like(SFunction<O, F> filed, String value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " like '%" + value + "%'");
        return this;
    }

    /**
     * @param column
     * @param value
     * @return
     */
    public EsQueryParameter leftLike(String column, String value) {
        whereCondition.add(column + " like '%" + value + "%'");
        return this;
    }

    public EsQueryParameter rightLike(String column, String value) {
        whereCondition.add(column + " like '" + value + "%'");
        return this;
    }

    /**
     *
     * @param pageSize
     * @return
     */
    public EsQueryParameter limit(Integer pageSize) {
        this.limit = pageSize;
        return this;
    }

    public boolean hashWhereCondition(){
        return !whereCondition.isEmpty();
    }

    public String getWhereSql() {
        return String.join(" and ", whereCondition);
    }

    public boolean hasLimit(){
        return limit>0;
    }
}
