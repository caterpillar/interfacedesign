package org.interfacedesign.base.exception;

/**
 * Created by lishaohua on 16-5-28.
 */
public class NotFindException extends DesignException {

    public NotFindException(String message) {
        super(ResponseStatusCode.NOT_FIND, message);
    }
}
