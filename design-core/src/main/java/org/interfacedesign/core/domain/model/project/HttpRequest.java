package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.util.Assert;
import org.interfacedesign.core.domain.model.utils.HttpMethod;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lishaohua on 16-6-18.
 */
@Entity
@Table(name = "http_request")
public class HttpRequest extends AbstractRequest {
    @Column(name = "request_url", length = 255)
    private String requestUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "http_method", length = 10)
    private HttpMethod httpMethod;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "httpRequest")
    private List<HttpParameter> httpParameters = new ArrayList<HttpParameter>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "httpRequest")
    private Set<HttpRequestHeaderValue> httpRequestHeaderValues = new HashSet<HttpRequestHeaderValue>();
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "http_interface_id")
    private HttpInterface httpInterface;

    public HttpRequest() {
    }

    public HttpRequest(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public boolean addParameter(HttpParameter httpParameter) {
        Assert.notNull(httpParameter, "不能添加空参数");
        return httpParameters.add(httpParameter);
    }

    public Collection<HttpParameter> getParameters() {
        return new ArrayList<HttpParameter>(this.httpParameters);
    }

    public boolean addHeaderValue(HttpRequestHeaderValue httpRequestHeaderValue) {
        Assert.notNull(httpRequestHeaderValue, "");
        return httpRequestHeaderValues.add(httpRequestHeaderValue);
    }

    public Collection<HttpRequestHeaderValue> getHeaderValues() {
        return new HashSet<HttpRequestHeaderValue>(this.httpRequestHeaderValues);
    }
}
