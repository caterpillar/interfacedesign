package org.interfacedesign.core.domain.model.project;

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
    @OneToMany
    private Set<HttpResponseHeaderValue> responseHeaderValues;
    @Lob
    @Column(name = "response_body")
    private String responseBody;





}
