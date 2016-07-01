package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<HttpResponseBodyExample> responseBodyExamples;

    public HttpResponseBody(String alps, HttpResponse httpResponse) {
        this.alps = alps;
        setHttpResponse(httpResponse);
        responseBodyExamples = new HashSet<HttpResponseBodyExample>();
    }

    public void addResponseBodyExample(MessageProtocol responseMessageProtocol, String responseBodyExample) {
        if(responseBodyExamples == null) {
            responseBodyExamples = new HashSet<HttpResponseBodyExample>();
        }
        HttpResponseBodyExample responseBodyExample1 = new HttpResponseBodyExample(responseMessageProtocol, responseBodyExample, this);
        responseBodyExamples.add(responseBodyExample1);
    }

    private void setHttpResponse(HttpResponse httpResponse) {
        Validate.notNull(httpResponse);
        this.httpResponse = httpResponse;
    }
    HttpResponseBody(){}
}
