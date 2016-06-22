package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.design.value.HttpHeader;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-18.
 */
@MappedSuperclass
public abstract class HttpHeaderValue extends LongIdEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", referencedColumnName = "name")
    protected HttpHeader httpHeader;
    @Column(name = "value", length = 255)
    protected String value;

}
