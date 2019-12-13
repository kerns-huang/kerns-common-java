package com.xd.mongo.convert;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

/**
 * bigdecimal convert
 *
 * @author xiaohei
 * @create 2019-12-13 下午5:18
 **/
public class BigDecimalConverter implements GenericConverter {


    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(BigDecimal.class, Double.class));
    }

    @Override
    public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        if(o instanceof BigDecimal){
            return ((BigDecimal)o).doubleValue();
        }
        return null;
    }
}
