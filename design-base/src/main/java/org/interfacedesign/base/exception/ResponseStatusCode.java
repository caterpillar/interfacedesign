package org.interfacedesign.base.exception;

import javax.ws.rs.core.Response;

/**
 * Created by lishaohua on 16-5-28.
 */
public class ResponseStatusCode {
    public static int BAD_REQUEST = Response.Status.BAD_REQUEST.getStatusCode();
    public static int NOT_FIND = Response.Status.NOT_FOUND.getStatusCode();
    public static int INTERNAL_SERVER_ERROR = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    public static int UNAUTHORIZED = Response.Status.UNAUTHORIZED.getStatusCode();
}
