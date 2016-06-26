package org.interfacedesign.core.domain.model.role;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.design.entity.NameDescriptionEntity;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-24.
 */
@Entity(name = "team")
public class Team extends LongIdEntity {
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", length = 50, nullable = false)),
            @AttributeOverride(name = "description", column = @Column(name = "description", length = 255))
    }
    )
    protected NameDescriptionEntity nameDescription;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "team")
    protected Set<Designer> designers;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    protected Admin admin;

    public Team(String name, String description, Admin admin) {
        setName(name);
        setAdmin(admin);
        setDescription(description);
    }

    public void addDesigner(Designer designer) {
        Validate.notNull(designer, "需要添加的设计人员为空");
        this.designers.add(designer);
    }

    public String getName() {
        return nameDescription.getName();
    }

    public String getDescription() {
        return nameDescription.getDescription();
    }

    public Collection<Designer> getDesigners() {
        return new ArrayList<Designer>(designers);
    }

    public Admin getAdmin() {
        return admin;
    }

    private void setDescription(String description) {
        this.nameDescription.setDescription(description);
    }

    private void setAdmin(Admin admin) {
        Validate.notNull(admin);
        this.admin = admin;
    }

    private void setName(String name) {
        Validate.notEmpty(name, "小组名称不能为空");
        this.nameDescription.setName(name);
    }

}
