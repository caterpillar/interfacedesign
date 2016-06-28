package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.util.Assert;
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

    public HttpHeaderValue(HttpHeader httpHeader, String value) {
        Validate.notNull(httpHeader);
        this.httpHeader = httpHeader;
        Assert.lengthNoGreater(value, 255, "http header 长度不能超过255");
        this.value = value;
    }

    HttpHeaderValue(){}
}
