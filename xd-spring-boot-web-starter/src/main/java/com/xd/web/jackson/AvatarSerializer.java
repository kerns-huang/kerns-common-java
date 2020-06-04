package com.xd.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.xd.core.util.StringUtils;

import java.io.IOException;

public class AvatarSerializer extends StdScalarSerializer<String> {

    public AvatarSerializer() {
        super(String.class, false);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (StringUtils.isEmpty(value)) {
            gen.writeArrayFieldStart("/default/default_avatar.png");
        } else {
            gen.writeArrayFieldStart(value);
        }
    }
}
