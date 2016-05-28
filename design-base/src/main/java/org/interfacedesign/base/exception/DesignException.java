package org.interfacedesign.base.exception;

/**
 * Created by lishaohua on 16-5-25.
 */
public class DesignException extends RuntimeException {
    private final String errorCode;

    public DesignException(String errorCode) {
        this.errorCode = errorCode;
    }

    public DesignException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
