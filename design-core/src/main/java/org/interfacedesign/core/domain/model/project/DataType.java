package org.interfacedesign.core.domain.model.project;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "data_type")
public class DataType {
    private String name;
    private String description;
}
