package org.interfacedesign.auth.domain.model.authUser;

/**
 * Created by lishaohua on 16-5-25.
 */
public interface AuthUser {
    public boolean isActive();
    public void deActivate();
    public void activate();
}
