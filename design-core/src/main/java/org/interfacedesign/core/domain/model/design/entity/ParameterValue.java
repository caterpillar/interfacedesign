package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by lishaohua on 16-7-4.
 */
@MappedSuperclass
public class ParameterValue extends LongIdEntity {
    @Column(name = "value", length = 100, nullable = false)
    private String value;
    @Column(name = "value", length = 100)
    private String description;

    public ParameterValue(String value, String description) {
        setValue(value);
        this.description = description;
    }

    private void setValue(String value) {
        Validate.notNull(value, "值不能为空");
        this.value = value;
    }

    ParameterValue(){}
}
