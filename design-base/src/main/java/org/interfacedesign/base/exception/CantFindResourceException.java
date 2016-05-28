package org.interfacedesign.base.exception;

/**
 * Created by lishaohua on 16-5-28.
 */
public class CantFindResourceException extends DesignException {
    public static final String ERROR_CODE = "404";

    public CantFindResourceException(String message) {
        super(ERROR_CODE, message);
    }
}
