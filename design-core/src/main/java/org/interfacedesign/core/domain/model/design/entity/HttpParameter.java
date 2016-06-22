package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.design.value.DataType;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity(name = "http_parameter")
public class HttpParameter extends LongIdEntity {
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", length = 50, nullable = false)),
            @AttributeOverride(name = "description", column = @Column(name = "description", length = 255))
    }
    )
    private NameDescriptionEntity nameDescription;
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER, optional = true)
    @JoinColumn(name = "data_type", referencedColumnName = "name")
    private DataType dataType;
    @Column(name = "value", length = 255)
    private String value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "http_request_id")
    private HttpRequest httpRequest;
    

    public String getName() {
        if(this.nameDescription == null) {
            throw new IllegalStateException("获取参数名出错");
        }
        return this.nameDescription.getName();
    }

    public String getDescription() {
        if(this.nameDescription == null) {
            throw new IllegalStateException("获取参数描述出错");
        }
        return this.nameDescription.getDescription();
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getValue() {
        return value;
    }
}
