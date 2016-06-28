package org.interfacedesign.core.domain.model.project;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.util.Assert;
import org.interfacedesign.core.domain.model.design.entity.NameDescriptionEntity;
import org.interfacedesign.core.domain.model.role.Admin;

import javax.persistence.*;
import java.util.Date;

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
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", length = 50, nullable = false)),
            @AttributeOverride(name = "description", column = @Column(name = "description", length = 255))
    }
    )
    protected NameDescriptionEntity nameDescription;
    @Column(name = "create_time")
    protected Date createTime;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "create_admin_id")
    protected Admin creator;



    public Project() {
        this.nameDescription = new NameDescriptionEntity();
    }

    public Project(String name, String description, Admin creator) {
        this();
        this.nameDescription = new NameDescriptionEntity();
        setName(name);
        setDescription(description);
        setCreator(creator);
        this.createTime = new Date();
    }

    private void setCreator(Admin creator) {
        Validate.notNull(creator, "创建人（admin）id不能为空");
        this.creator = creator;
    }

    public void setName(String name) {
        Assert.notEmpty(name, "项目名称不能为空");
        Assert.lengthNoGreater(name, 50, "项目名称不能超过50个字符长度");
        this.nameDescription.setName(name);
    }

    public void setDescription(String description) {
        Assert.notEmpty(description, "项目描述不能为空");
        Assert.lengthNoGreater(description, 300, "项目描述不能超过300个字符长度");
        this.nameDescription.setDescription(description);
    }

    public String getName() {
        if (this.nameDescription == null) {
            throw new IllegalStateException("项目状态非法");
        }
        return this.nameDescription.getName();
    }

    public String getDescription() {
        if (this.nameDescription == null) {
            throw new IllegalStateException("项目状态非法");
        }
        return this.nameDescription.getDescription();
    }


}
