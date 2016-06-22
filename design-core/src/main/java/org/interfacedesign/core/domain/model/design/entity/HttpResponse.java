package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.core.domain.model.utils.HttpResponseStatus;

import javax.persistence.*;
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
    private Set<HttpResponseHeaderValue> responseHeaderValues;
    @OneToOne
    @JoinColumn(name = "response_body_id")
    private HttpResponseBody responseBody;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "http_interface_id")
    private HttpInterface httpInterface;






}
