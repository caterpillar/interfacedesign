package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.domain.model.design.value.HttpHeader;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-17.
 */
@Entity
@Table(name = "http_request_header_value")
public class HttpRequestHeaderValue extends HttpHeaderValue {
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "http_request_id")
    private HttpRequest httpRequest;


    public HttpRequestHeaderValue(HttpHeader httpHeader, String value, HttpRequest httpRequest) {
        super(httpHeader, value);
        Validate.notNull(httpRequest);
        this.httpRequest = httpRequest;
    }

    public HttpRequestHeaderValue(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    HttpRequestHeaderValue(){}

}
