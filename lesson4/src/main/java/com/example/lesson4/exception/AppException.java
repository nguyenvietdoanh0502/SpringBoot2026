package com.example.lesson4.exception;

public class AppException extends RuntimeException {
    private final int errorCode;
    public AppException(int errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }
}
