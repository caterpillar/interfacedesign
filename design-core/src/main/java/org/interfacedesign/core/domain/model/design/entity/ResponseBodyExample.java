package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-30.
 */
@Entity
public class ResponseBodyExample extends LongIdEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "response_message_protocol", length = 10)
    protected MessageProtocol responseMessageProtocol;
    @Lob
    @Column(name = "response_body_example")
    private String responseBodyExample;

}
