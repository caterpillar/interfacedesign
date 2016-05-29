package org.interfacedesign;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class SimpleResponse {
    private final Map<String, Object> _this = new HashMap<String, Object>();

    public SimpleResponse putMessage(String key, Object value) {
        _this.put(key, value);
        return this;
    }

    @JsonValue
    public Map<String, Object> toMap() {
        return _this;
    }
}