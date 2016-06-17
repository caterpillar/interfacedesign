package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "data_type")
public class DataType {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    /** 是否是自定义类型 */
    @Column(name = "is_customer")
    private String isCustomer;

    protected DataType() {
    }

    public DataType(String name, String description, String isCustomer) {
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

    public String getIsCustomer() {
        return isCustomer;
    }
}
