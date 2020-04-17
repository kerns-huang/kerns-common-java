package com.xd.elasticsearch.core.response;

import com.xd.core.reflect.ClassUtil;
import com.xd.core.util.StringUtils;
import com.xd.elasticsearch.repository.metadata.Column;
import com.xd.elasticsearch.repository.metadata.JsonResult;
import com.xd.json.JSONUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * es json 数据返回解析
 *
 * @author xiaohei
 * @create 2020-04-17 上午10:24
 **/
public class JSONResponseResolver implements ResponseResover {

    public <T> List<T> resover(String response, Class<T> resultClass)  {
        JsonResult jsonResult= JSONUtils.toObject(response, JsonResult.class);
        jsonResult.getColumns();
        List<Column> columnList= jsonResult.getColumns();
        List<T> result=new ArrayList<>(16);
        for(List<String> values:jsonResult.getRows()){
            T obj=  ClassUtil.newInstance(resultClass);
            for(int i=0;i<values.size();i++){
                try {
                    PropertyDescriptor propertyDescriptor= PropertyUtils.getPropertyDescriptor(obj,columnList.get(i).getName());
                    PropertyUtils.setSimpleProperty(obj, propertyDescriptor.getName(), fillValue(propertyDescriptor,values.get(i)));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            result.add(obj);
        }
        return result;

    }

    private static Object fillValue(PropertyDescriptor propertyDescriptor, String value) {
        if (propertyDescriptor.getPropertyType() == Integer.class) {
            return value==null?null:Integer.parseInt(value);
        }else if(propertyDescriptor.getPropertyType() ==int.class){
            return Integer.parseInt(value);
        }else if (propertyDescriptor.getPropertyType() == Long.class) {
            return value==null?null:Long.parseLong(value);
        }else if (propertyDescriptor.getPropertyType() == String.class) {
            return value;
        } else if (propertyDescriptor.getPropertyType() == Boolean.class) {
            return value==null?null:Boolean.valueOf(value);
        } else if (propertyDescriptor.getPropertyType() == BigDecimal.class) {
            return value==null?null:new BigDecimal(value);
        } else if(propertyDescriptor.getPropertyType()==Byte.class){
            return value==null?null:new Byte(value);
        }else if (propertyDescriptor.getPropertyType() == Date.class) {
            return StringUtils.isNotEmpty(value) ? new Date(Long.valueOf(value)): null;
        }
        throw new UnsupportedOperationException("不支持的数据格式," + propertyDescriptor.getPropertyType().getName());
    }
}
