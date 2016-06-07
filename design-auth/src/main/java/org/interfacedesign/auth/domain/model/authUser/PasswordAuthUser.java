package org.interfacedesign.auth.domain.model.authUser;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by lishaohua on 16-5-25.
 */
@Entity
@Table(name = "auth_user")
public class PasswordAuthUser extends AbstractAuthUser {
    private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z]\\w{5,15}$");
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;


    public PasswordAuthUser() {
    }

    public PasswordAuthUser(String userName, String password) {
        if(StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(!USER_NAME_PATTERN.matcher(userName).find()) {
            throw new IllegalArgumentException("用户名必须以字母开头，且只能包含数字和下划线的6到16个字符的字符串");
        }
        if(StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        this.userName = userName;
        this.password = password;
        setIsActive(true);
        setJoinTime(new Date());
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
