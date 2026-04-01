package com.example.lesson3.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
