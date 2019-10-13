package com.jinternal.employee.exception;

public class RestException extends BaseException {

    public RestException(Object... messageArgs) {
        super(messageArgs);
    }

    public RestException(BaseException ex) {
        super(ex);
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... messageArgs) {
        super(message, cause, enableSuppression, writableStackTrace, messageArgs);
    }

    public RestException(String message, Throwable cause, Object... messageArgs) {
        super(message, cause, messageArgs);
    }

    public RestException(String message, Object... messageArgs) {
        super(message, messageArgs);
    }

    public RestException(Throwable cause, Object... messageArgs) {
        super(cause, messageArgs);
    }
}