package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.role.Designer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lishaohua on 16-6-21.
 */
@Entity(name = "design_stage")
public class DesignStage extends LongIdEntity {
    @Column(name = "begin_time")
    private Date beginTime;
    @Column(name = "end_time")
    private Date endTime;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "interface_id")
    private AbstractInterface abstractInterface;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "designer_id")
    private Designer designer;

}
