package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;

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


}
