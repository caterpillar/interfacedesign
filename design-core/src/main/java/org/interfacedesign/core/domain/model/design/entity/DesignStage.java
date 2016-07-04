package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.role.Designer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lishaohua on 16-6-21.
 */
@Entity(name = "design_stage")
public class DesignStage extends LongIdEntity implements Comparable<DesignStage> {
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


    public DesignStage(AbstractInterface abstractInterface, Designer designer) {
        setInterface(abstractInterface);
        setDesigner(designer);
        this.beginTime = new Date();
    }

    private void setDesigner(Designer designer) {
        Validate.notNull(designer);
        this.designer = designer;
    }

    private void setInterface(AbstractInterface abstractInterface) {
        Validate.notNull(abstractInterface);
        this.abstractInterface = abstractInterface;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int compareTo(DesignStage o) {
        if(o == null || o.getBeginTime() == null) {
            return 1;
        }
        if(this.beginTime == null) {
            return -1;
        }
        return this.beginTime.compareTo(o.getBeginTime());
    }

    DesignStage(){}
}
