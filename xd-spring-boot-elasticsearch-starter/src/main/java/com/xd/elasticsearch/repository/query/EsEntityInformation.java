package com.xd.elasticsearch.repository.query;

import org.springframework.data.repository.core.EntityInformation;

/**
 * es 的查询返回类型
 *
 * @author xiaohei
 * @create 2020-04-15 下午3:08
 **/
public class EsEntityInformation<T, ID> implements EntityInformation<T, ID> {
    @Override
    public boolean isNew(T entity) {
        return false;
    }

    @Override
    public ID getId(T entity) {
        return null;
    }

    @Override
    public Class<ID> getIdType() {
        return null;
    }

    @Override
    public Class<T> getJavaType() {
        return null;
    }
}
