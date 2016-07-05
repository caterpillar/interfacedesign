package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.domain.model.utils.HttpResponseStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-18.
 */
@Entity
@Table(name = "http_response")
public class HttpResponse extends AbstractResponse {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 5, nullable = false)
    private HttpResponseStatus status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "httpResponse")
    private Set<HttpResponseHeader> responseHeaderValues = new HashSet<HttpResponseHeader>();
    @OneToOne
    @JoinColumn(name = "response_body_id")
    private HttpResponseBody responseBody;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "http_interface_id")
    private HttpInterface httpInterface;

    public HttpResponse(HttpInterface httpInterface) {
        this(HttpResponseStatus.OK, null, httpInterface);
    }

    public HttpResponse(HttpResponseStatus status,
                        HttpResponseBody responseBody, HttpInterface httpInterface) {
        setHttpResponseStatus(status);
        setResponseBody(responseBody);
        setHttpInterface(httpInterface);
    }

    void setResponseBody(HttpResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    void setHttpInterface(HttpInterface httpInterface) {
        Validate.notNull(httpInterface);
        this.httpInterface = httpInterface;
    }

    void setHttpResponseStatus(HttpResponseStatus status) {
        Validate.notNull(status);
        this.status = status;
    }

    HttpResponse() {
    }
}
