package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.util.Assert;
import org.interfacedesign.core.domain.model.design.value.DataType;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity(name = "http_request_parameter")
public class HttpRequestParameter extends LongIdEntity {
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", length = 50, nullable = false)),
            @AttributeOverride(name = "description", column = @Column(name = "description", length = 255))
    }
    )
    private NameDescriptionEntity nameDescription;
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER, optional = true)
    @JoinColumn(name = "data_type", referencedColumnName = "name")
    private DataType dataType;
//    @Column(name = "value", length = 255)
//    private String value;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "httpRequestParameter")
    private Set<HttpRequestParameterValue> requestParameterValues = new HashSet<HttpRequestParameterValue>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "http_request_id")
    private HttpRequest httpRequest;

    public HttpRequestParameter(String name, String description, DataType dataType, String value, HttpRequest httpRequest) {
        setNameAndDescription(name, description);
        setDateType(dataType);
        setValue(value);
        setHttpRequest(httpRequest);
    }

    public HttpRequestParameter(String name,String description, DataType dataType, Set<EnumParameterValue> enumParameterValues , HttpRequest httpRequest) {
        setNameAndDescription(name, description);
        setDateType(dataType);
        setValue(enumParameterValues);
        setHttpRequest(httpRequest);
    }

    private void setValue(Set<EnumParameterValue> enumParameterValues) {
        if(!CollectionUtils.isEmpty(enumParameterValues)) {
            for(EnumParameterValue enumParameterValue : enumParameterValues) {
                this.requestParameterValues.add(new HttpRequestParameterValue(enumParameterValue, this));
            }
        }
    }

    private void setNameAndDescription(String name, String description) {
        Validate.notNull(name, "参数名称不能为空");
        Assert.lengthNoGreater(name, 50, "参数名称长度不能大于50");
        Assert.lengthNoGreater(description, 255, "参数描述长度不能大于255");
        this.nameDescription = new NameDescriptionEntity(name, description);
    }

    private void setDateType(DataType dataType) {
        Validate.notNull(dataType, "参数数据类型不能为空");
        this.dataType = dataType;
    }

    private void setValue(String value) {
        Assert.lengthNoGreater(value, 255, "参数样例值长度不能大于255");
        requestParameterValues.add(new HttpRequestParameterValue(value, null, this));
    }

    private void setHttpRequest(HttpRequest httpRequest) {
        Validate.notNull(httpRequest);
        this.httpRequest = httpRequest;
    }


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
        if(CollectionUtils.isEmpty(this.requestParameterValues)) {
            return null;
        }
        return this.requestParameterValues.toArray(new HttpRequestParameterValue[]{})[0].getValue();
    }


    HttpRequestParameter() {
    }

    public static class EnumParameterValue {
        private final String value;
        private final String description;

        public EnumParameterValue(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            EnumParameterValue that = (EnumParameterValue) o;

            return new EqualsBuilder()
                    .append(value, that.value)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(value)
                    .toHashCode();
        }
    }
}
