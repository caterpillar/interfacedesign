package org.interfacedesign.auth.domain.model;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.base.exception.NotFindException;
import org.interfacedesign.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-5-25.
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {
    public static String DEFAULT_PASSWORD = "111111";
    public static String DEFAULT_PASSWORD_MD5 = MD5Util.string2MD5(DEFAULT_PASSWORD);
    @Autowired
    private PasswordAuthUserRepository passwordAuthUserRepository;

    public PasswordAuthUser getPasswordAuthUser(String userName, String password) {
        if(StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        String passwordMd5 = MD5Util.string2MD5(password);
        return passwordAuthUserRepository.findByUserNameAndPassword(userName, passwordMd5);
    }

    @Transactional
    public void addAuthUser(String userName, String password) {
        if(StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        PasswordAuthUser passwordAuthUser = passwordAuthUserRepository.findByUserName(userName);
        if(passwordAuthUser != null) {
            throw new IllegalArgumentException("用户名重复");
        }
        String passwordMd5 = DEFAULT_PASSWORD_MD5;
        if(StringUtils.isNotEmpty(password)) {
            passwordMd5 = MD5Util.string2MD5(password);
        }
        passwordAuthUserRepository.save(new PasswordAuthUser(userName, passwordMd5));
    }

    @Transactional
    public void activate(String userName) {
        PasswordAuthUser passwordAuthUser = getPasswordAuthUser(userName);
        passwordAuthUser.activate();
    }

    @Transactional
    public void deactivate(String userName) {
        PasswordAuthUser passwordAuthUser = getPasswordAuthUser(userName);
        passwordAuthUser.deActivate();
    }

    public PasswordAuthUser getPasswordAuthUser(String userName) {
        if(StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("userName参数不能为空");
        }
        PasswordAuthUser passwordAuthUser = passwordAuthUserRepository.findByUserName(userName);
        if(passwordAuthUser == null) {
            throw new NotFindException(userName + "用户不存在");
        }
        return passwordAuthUser;
    }


}
