package com.xd.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.xd.core.util.StringUtils;

import java.io.IOException;

/**
 * 添加默认图片相关序列化
 *
 * @author xiaohei
 * @create 2020-04-13 下午2:17
 **/
public class PrefixUrlSerializer extends StdScalarSerializer<String> {

    private String prefix;

    public PrefixUrlSerializer(String prefix) {
        super(String.class, false);
        this.prefix = prefix;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        value = StringUtils.replace(value, "/?upload", "");
        if (StringUtils.isEmpty(value) && "avatar".equals(prefix)) {
            gen.writeString("/default_avatar.png");
        } else {
            gen.writeString(prefix + "/" + value + ".png");
        }
    }
}
