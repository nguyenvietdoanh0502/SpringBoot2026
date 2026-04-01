package com.example.btvn3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(int code, String message){
        this(code,message,null);
    }
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(200,"Thành công",data);
    }
    public static <T> ApiResponse<T> created(T data){
        return new ApiResponse<>(201,"Tạo thành công",data);
    }
    public static <T> ApiResponse<T> deleted(){
        return new ApiResponse<>(200,"Xóa thành công");
    }
    public static <T> ApiResponse<T> error(int code,String message){
        return new ApiResponse<>(code,message);
    }
}
