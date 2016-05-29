package org.interfacedesign.auth.domain.exception;

import org.interfacedesign.base.exception.DesignException;

/**
 * Created by lishaohua on 16-5-25.
 */
public class AuthException extends DesignException {

    public AuthException(int status, String message) {
        super(status, message);
    }

}
