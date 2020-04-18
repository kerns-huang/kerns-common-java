package com.xd.elasticsearch.repository.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @author xiaohei
 * @create 2020-04-18 上午10:18
 **/
public abstract class AbstractQuery<F extends AbstractQuery<F>> implements Compare,Nested<F> {

    /**
     * 具体传入的参数，如果是object 对象，做一层转换
     */
    protected final List<String> whereCondition = new ArrayList<>();
    /**
     *  and or 拼接查询
     */
    protected final Map<String,F> nestedQuery=new HashMap<>();

    public static final AtomicLong nestedQuerySeq=new AtomicLong(0);

    public  F or(Consumer<F> consumer) {
        String key= addNestedCondition(consumer);
        whereCondition.add(" or ("+key+")");
        return (F)this;
    }

    public F and(Consumer<F> consumer){
        String key= addNestedCondition(consumer);
        whereCondition.add(" and ("+key+")");
        return (F)this;
    }




    private String getNestedQueryKey() {
        Long seq= nestedQuerySeq.getAndAdd(1);
        String key="nested"+seq;
        return key;
    }

    /**
     * 多重嵌套查询条件
     *
     */
    protected String addNestedCondition(Consumer<F> consumer) {
        String ss= getNestedQueryKey();
        final F instance = getInstance();
        consumer.accept(instance);
        nestedQuery.put(ss,instance);
        return ss;
    }

    abstract  <F extends AbstractQuery> F getInstance();
}
