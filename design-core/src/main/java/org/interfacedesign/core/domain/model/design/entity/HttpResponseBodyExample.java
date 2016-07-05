package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-30.
 */
@Entity(name = "http_response_body_example")
public class HttpResponseBodyExample extends LongIdEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "response_message_protocol", length = 10)
    protected MessageProtocol responseMessageProtocol;
    @Lob
    @Column(name = "response_body_example")
    private String responseBodyExample;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "http_response_body_id")
    private HttpResponseBody responseBody;

    public HttpResponseBodyExample(MessageProtocol responseMessageProtocol, HttpResponseBody responseBody) {
        Validate.notNull(responseMessageProtocol);
        this.responseMessageProtocol = responseMessageProtocol;
        generateResponseBodyExample(responseMessageProtocol);
        Validate.notNull(responseBody);
        this.responseBody = responseBody;
    }

    private void generateResponseBodyExample(MessageProtocol responseMessageProtocol) {
        //TODO 有待考虑实现
        this.responseBodyExample = responseMessageProtocol.toString() + "--" + "example";
    }

    public MessageProtocol getResponseMessageProtocol() {
        return responseMessageProtocol;
    }

    public void setResponseMessageProtocol(MessageProtocol responseMessageProtocol) {
        this.responseMessageProtocol = responseMessageProtocol;
    }

    public String getResponseBodyExample() {
        return responseBodyExample;
    }

    public void setResponseBodyExample(String responseBodyExample) {
        this.responseBodyExample = responseBodyExample;
    }
}
