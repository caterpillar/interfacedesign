package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-7-4.
 */
@Entity(name = "http_request_parameter_value")
public class HttpRequestParameterValue extends ParameterValue {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "http_request_parameter_id")
    private HttpRequestParameter httpRequestParameter;


    public HttpRequestParameterValue(String value, String description, HttpRequestParameter httpRequestParameter) {
        super(value, description);
        Validate.notNull(httpRequestParameter);
        this.httpRequestParameter = httpRequestParameter;
    }

    HttpRequestParameterValue() {
    }
}
