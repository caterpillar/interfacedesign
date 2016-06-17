package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lishaohua on 16-6-17.
 */
@Entity
@Table(name = "http_header_value")
public class HttpHeaderValue extends LongIdEntity {
    @Column
    private HttpHeader httpHeader;
    @Column
    private String value;



}
