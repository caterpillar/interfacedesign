package org.interfacedesign.core.domain.model.role;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.design.entity.NameDescriptionEntity;

import javax.persistence.*;
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



}
