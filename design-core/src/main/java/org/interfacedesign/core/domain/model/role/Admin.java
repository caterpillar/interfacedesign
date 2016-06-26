package org.interfacedesign.core.domain.model.role;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 20:23
 * 实现业务需求
 */
@Entity
@Table(name = "admin")
public class Admin extends TeamMember {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "admin")
    private Set<Designer> designers;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "admin")
    private Set<Team> teams;

    public Admin(String firstName, String lastName, String mobilePhone, String email, String nickName,
                 String resourceAddress) {
        super(firstName, lastName, mobilePhone, email, nickName, resourceAddress);
    }

    public void addDesigner(Designer designer) {
        if (CollectionUtils.isEmpty(designers)) {
            designers = new HashSet<Designer>();
        }
        designer.setAdmin(this);
        designers.add(designer);
    }

    public Team buildTeam(String name, String description) {
        Team team = new Team(name, description, this);
        if(teams == null) {
            teams = new HashSet<Team>();
        }
        this.teams.add(team);
        return team;
    }

    public void assignTeam(Designer designer, Team team) {
        Validate.notNull(designer, "所有分配的设计者不能为空");
        designer.assignTeam(team);
    }

    public Collection<Designer> getDesigners() {
        if (designers == null) return new ArrayList<Designer>();
        return new ArrayList<Designer>(designers);
    }

    public Collection<Team> getTeams() {
        if (teams == null) return new ArrayList<Team>();
        return new ArrayList<Team>(teams);
    }

    public boolean deleteDesigner(Designer designer) {
        if (designer == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(designers)) {
            return false;
        }
        return designers.remove(designer);
    }

    Admin() {
    }

}
