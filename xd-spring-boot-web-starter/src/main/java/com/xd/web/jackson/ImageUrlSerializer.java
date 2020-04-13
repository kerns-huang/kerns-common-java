package com.xd.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.xd.annotations.web.response.ImageUrl;
import com.xd.core.util.StringUtils;
import lombok.Setter;

import java.io.IOException;

/**
 * 图片信息序列化
 *
 * @author xiaohei
 * @create 2020-04-10 下午3:31
 **/
@Setter
public  class ImageUrlSerializer extends StdScalarSerializer<String> implements ContextualSerializer {

    public ImageUrlSerializer() {
        super(String.class,false);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        ImageUrl imageUrl = beanProperty.getAnnotation(ImageUrl.class);
        if (imageUrl == null) {
            //如果没有注解，返回正常的string串
            return new StringSerializer();
        }
        if(imageUrl.group()!=null){
           switch (imageUrl.group()){
               case "country":
               case "pay_type":
               case "vip_icon":
               case "symbol":
               case "avatar":
                   return new PrefixUrlSerializer(imageUrl.group());
               default:
                   return new StringSerializer();
           }
        }
        return this;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
              jsonGenerator.writeString("");
    }

}
