package org.interfacedesign.auth.domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by lishaohua on 16-6-6.
 */
@Entity
@Table(name = "users")
public class Users extends IdEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany
    @JoinTable(name="users_role", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
