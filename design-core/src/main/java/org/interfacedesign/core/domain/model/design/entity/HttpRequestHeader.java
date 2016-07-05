package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.interfacedesign.core.domain.model.design.value.HttpHeader;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-17.
 */
@Entity
@Table(name = "http_request_header")
public class HttpRequestHeader extends AbstractHttpHeader {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "http_request_id")
    private HttpRequest httpRequest;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "httpRequestHeader")
    private Set<HttpRequestHeaderValue> httpRequestHeaderValues = new HashSet<HttpRequestHeaderValue>();


    public HttpRequestHeader(HttpHeader httpHeader, String value, HttpRequest httpRequest) {
        super(httpHeader);
        setHeaderValue(httpHeader, value);
        setHttpRequest(httpRequest);
    }

    public HttpRequestHeader(HttpHeader httpHeader, Set<AbstractValueDesc.EnumValue> enumHeaderValues, HttpRequest httpRequest) {
        super(httpHeader);
        setHeaderValue(httpHeader, enumHeaderValues);
        setHttpRequest(httpRequest);
    }

    private void setHeaderValue(HttpHeader httpHeader, String value) {
        if (StringUtils.isEmpty(value)) {
            value = httpHeader.getDefaultValue();
        }
        HttpRequestHeaderValue httpRequestHeaderValue = new HttpRequestHeaderValue(value, null, this);
        httpRequestHeaderValues.add(httpRequestHeaderValue);
    }

    private void setHeaderValue(HttpHeader httpHeader, Set<AbstractValueDesc.EnumValue> enumHeaderValues) {
        if(!CollectionUtils.isEmpty(enumHeaderValues)) {
            for(AbstractValueDesc.EnumValue enumHeaderValue: enumHeaderValues) {
                httpRequestHeaderValues.add(new HttpRequestHeaderValue(enumHeaderValue, this));
            }
        } else {
            throw new IllegalArgumentException("http request header 值不能为空");
        }
    }

    public String getHeaderValue() {
        if (CollectionUtils.isEmpty(this.httpRequestHeaderValues)) {
            return null;
        }
        return this.httpRequestHeaderValues.toArray(new HttpRequestHeaderValue[]{})[0].getValue();
    }

    public Collection<HttpRequestHeaderValue> getHttpRequestHeaderValues() {
        return new ArrayList<HttpRequestHeaderValue>(httpRequestHeaderValues);
    }

    private void setHttpRequest(HttpRequest httpRequest) {
        Validate.notNull(httpRequest);
        this.httpRequest = httpRequest;
    }

    HttpRequestHeader() {
    }



}
