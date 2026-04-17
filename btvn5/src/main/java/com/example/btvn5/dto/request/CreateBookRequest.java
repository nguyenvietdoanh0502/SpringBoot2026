package com.example.btvn5.dto.request;

import com.example.btvn5.entity.Category;
import com.example.btvn5.entity.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBookRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 2, max = 200, message = "Tiêu đề phải có từ 2 đến 200 ký tự")
    private String title;

    @NotBlank(message = "ISBN không được để trống")
    private String isbn;

    @NotNull(message = "Danh mục không được để trống")
    private Category category;

    @NotNull(message = "Trạng thái không được để trống")
    private Status status;

    @NotNull(message = "Tổng số lượng bản sao không được để trống")
    @Min(value = 1, message = "Tổng số lượng bản sao phải lớn hơn hoặc bằng 1")
    private Integer totalCopies;

    @NotNull(message = "Số lượng bản sao có sẵn không được để trống")
    @Min(value = 0, message = "Số lượng bản sao có sẵn không được là số âm")
    private Integer availableCopies;


    private Integer publishedYear;

    @NotNull(message = "ID tác giả không được để trống")
    private Long authorId;
}
