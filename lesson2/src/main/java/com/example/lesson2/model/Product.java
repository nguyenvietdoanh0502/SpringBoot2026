package com.example.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private double price;
}
