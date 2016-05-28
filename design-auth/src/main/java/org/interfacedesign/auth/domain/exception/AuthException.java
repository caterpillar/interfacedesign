package org.interfacedesign.auth.domain.exception;

import org.interfacedesign.base.exception.DesignException;

/**
 * Created by lishaohua on 16-5-25.
 */
public class AuthException extends DesignException {

    public AuthException(String errorCode) {
        super(errorCode);
    }

    public AuthException(String errorCode, String message) {
        super(errorCode, message);
    }


}
