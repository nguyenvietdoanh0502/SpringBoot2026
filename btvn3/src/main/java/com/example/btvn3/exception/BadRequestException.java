package com.example.btvn3.exception;

public class BadRequestException extends AppException {
    public BadRequestException(String message){
        super(400,message);
    }
}
