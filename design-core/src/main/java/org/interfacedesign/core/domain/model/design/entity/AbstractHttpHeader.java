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
public abstract class AbstractHttpHeader extends LongIdEntity {
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "name", referencedColumnName = "name")
    protected HttpHeader httpHeader;

    public AbstractHttpHeader(HttpHeader httpHeader) {
        Validate.notNull(httpHeader);
        this.httpHeader = httpHeader;
    }

    public HttpHeader getHttpHeader() {
        return httpHeader;
    }

    AbstractHttpHeader(){}
}
