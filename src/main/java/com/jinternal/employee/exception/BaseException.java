package com.jinternal.employee.exception;


public class BaseException extends Exception {

    private static final long serialVersionUID = 4733328770650586880L;

    private final Object messageArgs[];

    public BaseException(Object ... messageArgs) {
        super();
        this.messageArgs = messageArgs;
    }

    public BaseException(BaseException ex) {
        super(ex.getMessage(),ex);
        this.messageArgs =ex.getMessageArgs();
    }


    public BaseException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace,Object ... messageArgs) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.messageArgs = messageArgs;
    }

    public BaseException(String message, Throwable cause,Object ... messageArgs) {
        super(message, cause);
        this.messageArgs = messageArgs;
    }

    public BaseException(String message,Object ... messageArgs) {
        super(message);
        this.messageArgs = messageArgs;
    }

    public BaseException(Throwable cause,Object ... messageArgs) {
        super(cause);
        this.messageArgs = messageArgs;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }
}