package com.example.btvn3.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateStudentRequest {
    @NotBlank(message = "Họ tên không được để trống")
    private String name;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng hợp lệ")
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải gồm đúng 10 chữ số và bắt đầu bằng số 0")
    private String phone;
    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate dateOfBirth;
    @NotNull(message = "GPA không được để trống")
    @DecimalMin(value = "0.0", message = "GPA tối thiểu là 0.0")
    @DecimalMax(value = "4.0", message = "GPA tối đa là 4.0")
    private Double gpa;
    @NotBlank(message = "Chuyên ngành không được để trống")
    private String major;
    @NotNull(message = "Năm học không được để trống")
    @Min(value = 1, message = "Năm học thấp nhất là 1")
    @Max(value = 6, message = "Năm học cao nhất là 6")
    private Integer year;
}
