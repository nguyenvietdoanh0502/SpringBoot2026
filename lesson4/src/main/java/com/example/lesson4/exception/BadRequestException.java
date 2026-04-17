package com.example.lesson4.exception;

public class BadRequestException extends AppException {
    public BadRequestException(String message){
        super(400,message);
    }
}
