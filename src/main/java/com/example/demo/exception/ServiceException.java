package com.example.demo.exception;

import javax.xml.ws.Service;

public class ServiceException extends RuntimeException {
    private ErrorCode errorCode;

    public ServiceException (ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getErrorNumber() {
        return errorCode.getNumber();
    }

    public String getErrorMessage() {
        return errorCode.getMessage();
    }
}
