package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "http_parameter")
public class HttpParameter extends LongIdEntity {
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "description", column = @Column(name = "description"))
    }
    )
    private NameDescriptionEntity nameDescription;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="data_type")
    private DataType dataType;

    private HttpInterface httpInterface;
    

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
}
