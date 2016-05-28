package org.interfacedesign.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

import java.io.IOException;

// http://digitaljoel.nerd-herders.com/2015/03/18/how-to-make-jackson-serialize-null-strings-differently-than-null-objects/
// We need to customize the DefaultSerializerProvider so that when it is looking for a NullSerializer it
// will use one that is class sensitive, writing strings as "" and everything else using the default value.
public class CustomSerializerProvider extends DefaultSerializerProvider {

    // A couple of constructors and factory methods to keep the compiler happy
    public CustomSerializerProvider() {
        super();
    }

    public CustomSerializerProvider(CustomSerializerProvider provider, SerializationConfig config,
                                    SerializerFactory jsf) {
        super(provider, config, jsf);
    }

    @Override
    public CustomSerializerProvider createInstance(SerializationConfig config,
                                                   SerializerFactory jsf) {
        return new CustomSerializerProvider(this, config, jsf);
    }

    // This is the interesting part.  When the property has a null value it will call this method to get the
    // serializer for that null value.  At this point, we have the BeanProperty, which contains information about
    // the field that we are trying to serialize (including the type!)  So we can discriminate on the type to determine
    // which serializer is used to output the null value.
    @Override
    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
        Class<?> clazz = property.getType().getRawClass();
        if (clazz.equals(String.class)) {
            return EmptyStringSerializer.INSTANCE;
        }
        if (clazz.isArray()) {
            return EmptyArraySerializer.INSTANCE;
        }
        if (Number.class.isAssignableFrom(clazz)) {
            return EmptyNumberSerializer.INSTANCE;
        } else {
            return super.findNullValueSerializer(property);
        }
    }
}

// This is our fancy serializer that takes care of writing the value desired in the case of a null string.  We could
// write whatever we want in here, but in order to maintain backward compatibility we choose the empty string
// instead of something like "joel is awesome."
class EmptyStringSerializer extends JsonSerializer<Object> {
    public static final JsonSerializer<Object> INSTANCE = new EmptyStringSerializer();

    private EmptyStringSerializer() {
    }

    // Since we know we only get to this seralizer in the case where the value is null and the type is String, we can
    // do our handling without any additional logic and write that empty string we are so desperately wanting.
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        jsonGenerator.writeString("");
    }
}

class EmptyArraySerializer extends JsonSerializer<Object> {
    public static final JsonSerializer<Object> INSTANCE = new EmptyArraySerializer();

    private EmptyArraySerializer() {
    }

    // Since we know we only get to this seralizer in the case where the value is null and the type is String, we can
    // do our handling without any additional logic and write that empty string we are so desperately wanting.
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
    }
}

class EmptyNumberSerializer extends JsonSerializer<Object> {
    public static final JsonSerializer<Object> INSTANCE = new EmptyNumberSerializer();

    private EmptyNumberSerializer() {
    }

    // Since we know we only get to this seralizer in the case where the value is null and the type is String, we can
    // do our handling without any additional logic and write that empty string we are so desperately wanting.
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(0);
    }
}