package org.interfacedesign.core.domain.model.project;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:24
 * Project
 */
@Entity
@Table(name = "project")
public class Project extends LongIdEntity {
    @Column(length = 50)
    private String name;
    @Column(length = 300)
    private String description;

    public Project(){}

    public Project(String name, String description) {
        setName(name);
        setDescription(description);
    }

    void setName(String name) {
        Assert.notEmpty(name, "项目名称不能为空");
        Assert.lengthNoGreater(name, 50, "项目名称不能超过50个字符长度");
        this.name = name;
    }

    void setDescription(String description) {
        Assert.notEmpty(description, "项目描述不能为空");
        Assert.lengthNoGreater(description, 300, "项目描述不能超过300个字符长度");
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
