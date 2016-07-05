package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Created by lishaohua on 16-7-4.
 */
@MappedSuperclass
public abstract class AbstractValueDesc extends LongIdEntity {
    @Column(name = "value", length = 100, nullable = false)
    private String value;
    @Column(name = "description", length = 100)
    private String description;

    public AbstractValueDesc(String value, String description) {
        setValue(value);
        this.description = description;
    }

    public AbstractValueDesc(EnumValue enumValue) {
        Validate.notNull(enumValue);
        setValue(enumValue.getValue());
        this.description = enumValue.getDescription();
    }

    private void setValue(String value) {
        Validate.notNull(value, "值不能为空");
        this.value = value;
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
        if (!super.equals(o)) return false;
        AbstractValueDesc that = (AbstractValueDesc) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    AbstractValueDesc(){}

    public static class EnumValue {
        private final String value;
        private final String description;

        public EnumValue(String value, String description) {
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
            EnumValue that = (EnumValue) o;
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
