package org.interfacedesign.auth.domain.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by lishaohua on 16-5-25.
 */
@MappedSuperclass
public class AbstractAuthUser implements AuthUser {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "join_time")
    private Date joinTime;

    protected void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    protected void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deActivate() {
        if(!isActive()) {
            throw new IllegalStateException("该用户处于禁用状态不许禁用");
        }
        this.isActive = false;
    }

    public void activate() {
        if(isActive()) {
            throw new IllegalStateException("该用户处于激活状态，不需要激活");
        }
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public Date getJoinTime() {
        return joinTime;
    }



}
