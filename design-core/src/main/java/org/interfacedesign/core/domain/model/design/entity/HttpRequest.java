package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
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
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "httpRequest")
    private List<HttpRequestParameter> httpRequestParameters = new ArrayList<HttpRequestParameter>();
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "httpRequest")
    private Set<HttpRequestHeader> httpRequestHeaders = new HashSet<HttpRequestHeader>();
    @OneToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "http_interface_id")
    private HttpInterface httpInterface;

    HttpRequest() {
    }

    public HttpRequest(String requestUrl, HttpInterface httpInterface) {
        this(requestUrl, HttpMethod.GET, httpInterface);
    }

    public HttpRequest(String requestUrl, HttpMethod httpMethod, HttpInterface httpInterface) {
        setRequestUrl(requestUrl);
        setHttpMethod(httpMethod);
        setHttpInterface(httpInterface);
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    private void setHttpInterface(HttpInterface httpInterface) {
        Validate.notNull(httpInterface);
        this.httpInterface = httpInterface;
    }

    void setHttpMethod(HttpMethod httpMethod) {
        if(httpMethod == null) {
            this.httpMethod = HttpMethod.GET;
        }
        this.httpMethod = httpMethod;
    }

    void setRequestUrl(String requestUrl) {
        Validate.notNull(requestUrl, "request url不能为空");
        Assert.lengthNoGreater(requestUrl, 255, "request url长度不能大于255");
        this.requestUrl = requestUrl;
    }

    boolean addParameter(HttpRequestParameter httpRequestParameter) {
        Validate.notNull(httpRequestParameter, "不能添加空参数");
        return httpRequestParameters.add(httpRequestParameter);
    }

    boolean addHttpHeaderValue(HttpRequestHeader httpRequestHeader) {
        Validate.notNull(httpRequestHeader, "不能添加空的http header");
        return httpRequestHeaders.add(httpRequestHeader);
    }

    public Collection<HttpRequestParameter> getParameters() {
        return new ArrayList<HttpRequestParameter>(this.httpRequestParameters);
    }

    public Collection<HttpRequestHeader> getHeaderValues() {
        if(httpRequestHeaders == null) {
            new ArrayList<HttpRequestHeader>();
        }
        return new ArrayList<HttpRequestHeader>(this.httpRequestHeaders);
    }
}
