package com.xd.elasticsearch.repository.query;

import com.xd.core.lambda.LambdaUtil;
import com.xd.core.lambda.SFunction;
import com.xd.elasticsearch.core.enums.OrderType;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * es 查询需要传入的参数
 *
 * @author xiaohei
 * @create 2020-04-15 下午6:47
 **/
public class EsQueryParameter extends AbstractQuery<EsQueryParameter> {

    /**
     * order 字段
     */
    private final List<String> orderCondition = new ArrayList<>();

    /**
     * 查询的字段
     */
    private final List<String> select = new ArrayList<>();
    @Getter
    private int limit = -1;

    public final <O> EsQueryParameter select(SFunction<O, ?>... fields) {
        if (ArrayUtils.isNotEmpty(fields)) {
            for (SFunction<O, ?> field : fields) {
                select.add(LambdaUtil.getCacheKey(field));
            }
        }
        return this;
    }

    @Override
    public <O, F> EsQueryParameter eq(SFunction<O, F> filed, Object value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " = " + value);
        return this;
    }


    @Override
    public <O, F> EsQueryParameter in(SFunction<O, F> filed, Collection<?> values) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + "in " + values.stream().map(i -> String.format("%s", i)).collect(Collectors.joining(",", "(", ")")) + " ");
        return this;
    }


    @Override
    public <O, F> EsQueryParameter ne(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " <= " + val);
        return this;
    }

    @Override
    public <O, F> EsQueryParameter gt(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " > " + val);
        return this;
    }

    @Override
    public <O, F> EsQueryParameter ge(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " >= " + val);
        return this;
    }

    @Override
    public <O, F> EsQueryParameter lt(SFunction<O, F> filed, Object val) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " < " + val);
        return this;
    }

    @Override
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
     * @param value
     * @return
     */
    @Override
    public <O, F> EsQueryParameter leftLike(SFunction<O, F> filed, String value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " like '%" + value + "%'");
        return this;
    }

    @Override
    public <O, F> EsQueryParameter rightLike(SFunction<O, F> filed, String value) {
        String column = LambdaUtil.getCacheKey(filed);
        whereCondition.add(column + " like '" + value + "%'");
        return this;
    }


    public <O, F> EsQueryParameter order(SFunction<O, F> filed, OrderType orderType) {
        String column = LambdaUtil.getCacheKey(filed);
        orderCondition.add(column + " " + orderType.getValue());
        return this;
    }

    /**
     * 多个字段倒叙排序
     *
     * @param fields
     * @param <O>
     * @param <F>
     * @return
     */
    public <O, F> EsQueryParameter orderDesc(SFunction<O, F>... fields) {
        for (SFunction<O, F> field : fields) {
            String column = LambdaUtil.getCacheKey(field);
            orderCondition.add(column + " " + OrderType.DESC.getValue());
        }
        return this;
    }

    public <O, F> EsQueryParameter order(SFunction<O, F>... fields) {
        for (SFunction<O, F> field : fields) {
            String column = LambdaUtil.getCacheKey(field);
            orderCondition.add(column);
        }
        return this;
    }

    public EsQueryParameter and(){
        whereCondition.add(" and ");
        return this;
    }

    public EsQueryParameter or(){
        whereCondition.add(" or ");
        return this;
    }

    public EsQueryParameter getInstance() {
        return new EsQueryParameter();
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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < whereCondition.size(); i++) {
            if (i == 0) {
                // 如果第一行数据包含 and or ，则去掉
                String first=whereCondition.get(i);
                if(StringUtils.containsAny(first,"and","or")){
                    first=first.replace("and","");
                    first=first.replace("or","");
                }
                builder.append(first);
            } else if (StringUtils.containsAny(whereCondition.get(i), "and", "or")) {
                builder.append(whereCondition.get(i));
            }else if(StringUtils.containsAny(whereCondition.get(i-1),"and", "or")){
                builder.append(whereCondition.get(i));
            }else {
                builder.append(" and ").append(whereCondition.get(i));
            }
        }

        String whereSql = builder.toString();
        if (nestedQuery.isEmpty()) {
            return whereSql;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, EsQueryParameter> entry : nestedQuery.entrySet()) {
            whereSql = StringUtils.replace(whereSql, entry.getKey(), entry.getValue().getWhereSql());
        }
        ;
        return whereSql;
    }

    public boolean hasOrderCondition() {
        return !orderCondition.isEmpty();
    }

    public String getOrderSql() {
        return String.join(" , ", orderCondition);
    }

    public boolean hasLimit() {
        return limit > 0;
    }


}
