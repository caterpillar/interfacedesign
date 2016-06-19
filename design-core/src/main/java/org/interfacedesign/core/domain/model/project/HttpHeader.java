package org.interfacedesign.core.domain.model.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.HttpMethod;

/**
 * Created by lishaohua on 16-6-17.
 */
@Entity
@Table(name = "http_header")
public class HttpHeader {
    @Id
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    /** header的默认值 */
    @Column(name = "default_value", length = 255)
    private String defaultValue;

    protected HttpHeader() {
    }

    public HttpHeader(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
