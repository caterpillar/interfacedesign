package org.interfacedesign.auth.domain.model;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.util.MD5Util;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * Created by lishaohua on 16-6-6.
 */
@Entity
@Table(name = "users")
public class Users extends LongIdEntity {
    private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z]\\w{5,15}$");
    private static final String DEFAULT_PASSWORD = "111111";
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name="users_role", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    protected Users(){}

    public Users(String name) {
        this(name, null);
    }

    public Users(String name, String password) {
        setName(name);
        modifyPassword(password);
        enabled();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(!USER_NAME_PATTERN.matcher(name).find()) {
            throw new IllegalArgumentException("用户名必须以字母开头，且只能包含数字和下划线的6到16个字符的字符串");
        }
        this.name = name;
    }

    public void modifyPassword(String password) {
        if(StringUtils.isEmpty(password)) {
            password = DEFAULT_PASSWORD;
        }
        this.password = MD5Util.string2MD5(password);
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void disabled() {
        this.enabled = Boolean.FALSE;
    }

    public void enabled() {
        this.enabled = Boolean.TRUE;
    }

    public Set<Role> getRoles() {
        if(CollectionUtils.isEmpty(this.roles)) {
            this.roles = new HashSet<Role>();
        }
        return new HashSet<Role>(roles);
    }

    public boolean addRole(Role role) {
        if(CollectionUtils.isEmpty(this.roles)) {
            this.roles = new HashSet<Role>();
        }

        return roles.add(role);
    }

}
