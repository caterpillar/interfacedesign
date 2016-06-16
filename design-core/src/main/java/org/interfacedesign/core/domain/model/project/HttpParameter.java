package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "http_parameter")
public class HttpParameter extends LongIdEntity {
    private String name;
    private DataType dataType;
}
