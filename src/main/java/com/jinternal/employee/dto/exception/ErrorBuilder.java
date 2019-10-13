package com.jinternal.employee.dto.exception;

import org.springframework.http.HttpStatus;

public class ErrorBuilder {
    private String message;
    private String code;
    private HttpStatus httpStatus;

    public ErrorBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorBuilder setCode(String errorCode) {
        this.code = errorCode;
        return this;
    }

    public ErrorBuilder setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }


    public Error build() {
        return new Error(message, code, httpStatus);
    }
}
