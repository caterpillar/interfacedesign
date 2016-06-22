package org.interfacedesign.core.domain.model.design.value;

import org.hibernate.mapping.Value;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.value.ValueObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "data_type")
public class DataType implements ValueObject {
    @Id
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    /** 是否是自定义类型 */
    @Column(name = "is_customer")
    private Boolean isCustomer;
    @Column(name = "default_value", length = 50)
    private String defaultValue;

    protected DataType() {
    }

    public DataType(String name, String description, Boolean isCustomer) {
        this.name = name;
        this.description = description;
        this.isCustomer = isCustomer;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsCustomer() {
        return isCustomer;
    }
}
