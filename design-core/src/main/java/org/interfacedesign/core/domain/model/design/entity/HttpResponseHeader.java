package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.domain.model.design.value.HttpHeader;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-17.
 */
@Entity
@Table(name = "http_response_header")
public class HttpResponseHeader extends AbstractHttpHeader {
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "http_response_id")
    private HttpResponse httpResponse;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "httpResponseHeader")
    private Set<HttpResponseHeaderValue> httpResponseHeaderValues = new HashSet<HttpResponseHeaderValue>();


    public HttpResponseHeader(HttpHeader httpHeader, String value, HttpResponse httpResponse) {
        super(httpHeader);
        setHeaderValue(httpHeader, value);
        setHttpResponse(httpHeader, httpResponse);
    }

    public HttpResponseHeader(HttpHeader httpHeader, Set<AbstractValueDesc.EnumValue> enumValues, HttpResponse httpResponse) {
        super(httpHeader);
        setHeaderValue(httpHeader, enumValues);
        setHttpResponse(httpHeader, httpResponse);
    }

    private void setHttpResponse(HttpHeader httpHeader, HttpResponse httpResponse) {
        Validate.notNull(httpHeader);
        this.httpResponse = httpResponse;
    }

    private void setHeaderValue(HttpHeader httpHeader,Set<AbstractValueDesc.EnumValue>  enumValues) {
        if(!CollectionUtils.isEmpty(enumValues)) {
            for(AbstractValueDesc.EnumValue enumValue : enumValues) {
                this.httpResponseHeaderValues.add(new HttpResponseHeaderValue(enumValue, this));
            }
        } else {
            throw new IllegalArgumentException("http response header 不能为空");
        }
    }

    HttpResponseHeader(){}


    public void setHeaderValue(HttpHeader httpHeader, String headerValue) {
        if (StringUtils.isEmpty(headerValue)) {
            headerValue = httpHeader.getDefaultValue();
        }
        HttpResponseHeaderValue httpResponseHeaderValue = new HttpResponseHeaderValue(headerValue, null, this);
        httpResponseHeaderValues.add(httpResponseHeaderValue);
    }
}
