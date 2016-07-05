package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-7-5.
 */
@Entity(name = "http_response_header_value")
public class HttpResponseHeaderValue extends AbstractValueDesc {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "http_response_header_id")
    private HttpResponseHeader httpResponseHeader;

    public HttpResponseHeaderValue(String value, String description, HttpResponseHeader httpResponseHeader) {
        super(value, description);
        setHttpResponseHeader(httpResponseHeader);
    }

    public HttpResponseHeaderValue(EnumValue enumValue, HttpResponseHeader httpResponseHeader) {
        super(enumValue);
        setHttpResponseHeader(httpResponseHeader);
    }

    private void setHttpResponseHeader(HttpResponseHeader httpResponseHeader) {
        Validate.notNull(httpResponseHeader);
        this.httpResponseHeader = httpResponseHeader;
    }

    HttpResponseHeaderValue(){}
}
