package com.jinternal.employee.exception;

public class ServiceException extends BaseException {

    public ServiceException(Object... messageArgs) {
        super(messageArgs);
    }

    public ServiceException(BaseException ex) {
        super(ex);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... messageArgs) {
        super(message, cause, enableSuppression, writableStackTrace, messageArgs);
    }

    public ServiceException(String message, Throwable cause, Object... messageArgs) {
        super(message, cause, messageArgs);
    }

    public ServiceException(String message, Object... messageArgs) {
        super(message, messageArgs);
    }

    public ServiceException(Throwable cause, Object... messageArgs) {
        super(cause, messageArgs);
    }
}