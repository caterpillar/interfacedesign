package org.interfacedesign.auth.domain.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-6.
 */
@Entity
@Table(name = "role")
public class Role extends IdEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany()
    @JoinTable(name = "users_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "users_id") })
    private Set<Users> usersSet;

    protected Role(){}

    public Role(String name) {
        this(name, null);
    }

    public Role(String name, String description) {
        setName(name);
        modifyDescription(description);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Role)) {
            return false;
        }
        Role tempRole = (Role) obj;
        if(this.name == null && tempRole.getName() == null) {
            return true;
        } else {
            if(this.name == null && tempRole.getName() != null) {
                return false;
            }
            if(this.name != null && tempRole.getName() == null) {
                return false;
            }
            return this.name.equals(tempRole.getName());
        }
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("角色名称不能为空");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void modifyDescription(String description) {
        this.description = description;
    }

    public Set<Users> getUsers() {
        if(this.usersSet == null) {
            this.usersSet = new HashSet<Users>();
        }
        return new HashSet<Users>(usersSet);
    }

    public boolean addUsers(Users users) {
        if(users == null) {
            throw new IllegalArgumentException("不能添加空的用户");
        }
        if(this.usersSet == null) {
            this.usersSet = new HashSet<Users>();
        }
        return this.usersSet.add(users);
    }

    public boolean removeUsers(Users users) {
        if(users == null) {
            throw new IllegalArgumentException("不能删除空的用户");
        }
        if(this.usersSet == null) {
            return false;
        }
        return this.usersSet.remove(users);
    }
}
