package org.interfacedesign.base.exception;

/**
 * Created by lishaohua on 16-5-25.
 */
public class DesignException extends RuntimeException {
    private final int status;

    public DesignException(int status) {
        this.status = status;
    }

    public DesignException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

}
