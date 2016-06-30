package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-19.
 */
@Entity(name = "http_response_body")
public class HttpResponseBody extends LongIdEntity {
    @Lob
    @Column(name = "alps")
    private String alps;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "http_response_id")
    private HttpResponse httpResponse;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "responseBody")
    private Set<ResponseBodyExample> responseBodyExamples;

}
