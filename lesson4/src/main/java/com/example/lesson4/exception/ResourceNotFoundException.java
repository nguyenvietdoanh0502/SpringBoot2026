package com.example.lesson4.exception;



public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String resource, String field, Object value){
        super(404, String.format("%s Không tìm thấy lỗi với %s: '%s'",resource,field,value));
    }
}
