package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lishaohua on 16-7-5.
 */
@Entity(name = "http_request_header_value")
public class HttpRequestHeaderValue extends AbstractValueDesc  {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "http_request_header_id")
    private HttpRequestHeader httpRequestHeader;

    public HttpRequestHeaderValue(String value, String description, HttpRequestHeader httpRequestHeader) {
        super(value, description);
        setHttpRequestHeader(httpRequestHeader);
    }

    public HttpRequestHeaderValue(AbstractValueDesc.EnumValue enumHeaderValue,
                                  HttpRequestHeader httpRequestHeader) {
        super(enumHeaderValue);
        setHttpRequestHeader(httpRequestHeader);
    }

    private void setHttpRequestHeader(HttpRequestHeader httpRequestHeader) {
        Validate.notNull(httpRequestHeader);
        this.httpRequestHeader = httpRequestHeader;
    }

    HttpRequestHeaderValue(){}
}
