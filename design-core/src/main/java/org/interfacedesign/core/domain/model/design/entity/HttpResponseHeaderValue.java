package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-17.
 */
@Entity
@Table(name = "http_response_header_value")
public class HttpResponseHeaderValue extends LongIdEntity {
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "http_response_id")
    private HttpResponse httpResponse;

}
