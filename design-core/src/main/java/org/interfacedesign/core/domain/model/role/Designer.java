package org.interfacedesign.core.domain.model.role;

import org.apache.commons.lang3.Validate;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:43
 * 实现业务需求
 */
@Entity
@Table(name = "designer")
public class Designer extends TeamMember {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    public Designer(){}

    public Designer(String firstName, String lastName, String mobilePhone, String email, String nickName, String photograph) {
        super(firstName, lastName, mobilePhone, email, nickName, photograph);
    }

    public void assignTeam(Team team) {
        Validate.notNull(team, "所分配小组不能为空");
        this.team = team;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Team getTeam() {
        return team;
    }
}
