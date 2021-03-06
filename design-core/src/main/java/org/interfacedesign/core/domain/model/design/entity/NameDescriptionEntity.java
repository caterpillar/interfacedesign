package org.interfacedesign.core.domain.model.design.entity;

import javax.persistence.Embeddable;

/**
 * Created by lishaohua on 16-6-13.
 */
@Embeddable
public class NameDescriptionEntity {
    private String name;
    private String description;

    public NameDescriptionEntity(){}

    public NameDescriptionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
