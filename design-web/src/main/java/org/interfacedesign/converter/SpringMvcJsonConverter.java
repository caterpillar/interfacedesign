package org.interfacedesign.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SpringMvcJsonConverter {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SpringMvcJsonConverter.class);

    private final ObjectMapper mapper;

    public SpringMvcJsonConverter() throws InstantiationException, IllegalAccessException {
        mapper = ObjectMapperFactory.newMapper(null);
    }

    public SpringMvcJsonConverter(String style) throws InstantiationException, IllegalAccessException {
        mapper = ObjectMapperFactory.newMapper(style);
    }

    public SpringMvcJsonConverter(String style, DefaultSerializerProvider provider) throws InstantiationException, IllegalAccessException {
        mapper = ObjectMapperFactory.newMapper(style);
        mapper.setSerializerProvider(provider);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    static class ObjectMapperFactory {
        static ObjectMapper newMapper(String style) {
            if ("always".equals(style)) {
                return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
            } else if ("nonEmpty".equals(style)) {
                return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            } else if ("nonDefault".equals(style)) {
                return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            } else if ("nonNull".equals(style)) {
                return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            } else {
                return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
            }
        }
    }
}