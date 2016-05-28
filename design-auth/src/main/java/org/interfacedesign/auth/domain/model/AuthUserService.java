package org.interfacedesign.auth.domain.model;

/**
 * Created by lishaohua on 16-5-25.
 */
public interface AuthUserService {
    PasswordAuthUser getPasswordAuthUser(String userName, String password);
    PasswordAuthUser getPasswordAuthUser(String userName);
    void addAuthUser(String userName, String password);
    void activate(String userName);
    void deactivate(String userName);
}
