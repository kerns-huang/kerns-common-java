package com.xd.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

/**
 * boolean 序列化
 * TODO 需要添加对imageUr和ImageUrls的处理
 * @author xiaohei
 * @create 2019-12-20 下午5:12
 **/
@JacksonStdImpl
public class BooleanSerializer extends StdScalarSerializer<Boolean> {
    public BooleanSerializer() {
        super(Boolean.class,false);
    }

    @Override
    public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(aBoolean==true?"1":"0");
    }
}
