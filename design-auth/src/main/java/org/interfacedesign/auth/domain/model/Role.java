package org.interfacedesign.auth.domain.model;

import javax.persistence.*;
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
    private Set<Users> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
