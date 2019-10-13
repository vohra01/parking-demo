package com.jinternal.employee.dto.exception;

import org.springframework.http.HttpStatus;

public class Error {

    private String message;

    private String code;

    private HttpStatus httpStatus;

    public Error(String message, String errorCode, HttpStatus httpStatus) {
        super();
        this.message = message;
        this.code = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
