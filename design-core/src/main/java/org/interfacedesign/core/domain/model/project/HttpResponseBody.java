package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-19.
 */
@Entity(name = "http_response_body")
public class HttpResponseBody extends LongIdEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "response_message_protocol", length = 10)
    protected MessageProtocol responseMessageProtocol;
    @Lob
    @Column(name = "alps")
    private String alps;
    @Lob
    @Column(name = "response_body_example")
    private String responseBodyExample;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "http_response_id")
    private HttpResponse httpResponse;
}
