package com.xd.elasticsearch.repository.query;

import com.xd.core.lambda.LambdaUtil;
import com.xd.core.lambda.SFunction;
import com.xd.elasticsearch.core.enums.OrderType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    private final List<String> orderCondition=new ArrayList<>();
    @Getter
    private int limit = -1;



    public <O, F> EsQueryParameter eq(SFunction<O, F> filed, Object value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " = " + value);
        return this;
    }


    public <O, F> EsQueryParameter in(SFunction<O, F> filed, Collection<?> values) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + "in " + values.stream().map(i -> String.format("%s", i)).collect(Collectors.joining(",", "(", ")")) + " ");
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
    public <O, F> EsQueryParameter leftLike(SFunction<O, F> filed, String value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " like '%" + value + "%'");
        return this;
    }

    public <O, F> EsQueryParameter rightLike(SFunction<O, F> filed, String value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " like '" + value + "%'");
        return this;
    }

    public <O, F> EsQueryParameter order(SFunction<O, F> filed, OrderType orderType){
        String column = LambdaUtil.getCacheKey(filed);
        orderCondition.add(column+" "+orderType.getValue());
        return this;
    }

    /**
     * 多个字段倒叙排序
     * @param fields
     * @param <O>
     * @param <F>
     * @return
     */
    public <O, F> EsQueryParameter orderDesc(SFunction<O, F>... fields){
        for(SFunction<O, F> field:fields){
            String column = LambdaUtil.getCacheKey(field);
            orderCondition.add(column+" "+OrderType.DESC.getValue());
        }
        return this;
    }

    public <O, F> EsQueryParameter order(SFunction<O, F>... fields){
        for(SFunction<O, F> field:fields){
            String column = LambdaUtil.getCacheKey(field);
            orderCondition.add(column);
        }
        return this;
    }

    /**
     * @param pageSize
     * @return
     */
    public EsQueryParameter limit(Integer pageSize) {
        this.limit = pageSize;
        return this;
    }

    public boolean hashWhereCondition() {
        return !whereCondition.isEmpty();
    }

    public String getWhereSql() {
        return String.join(" and ", whereCondition);
    }

    public boolean hasOrderCondition(){
        return !orderCondition.isEmpty();
    }

    public String getOrderSql(){
        return String.join(" , ",orderCondition);
    }

    public boolean hasLimit() {
        return limit > 0;
    }
}
