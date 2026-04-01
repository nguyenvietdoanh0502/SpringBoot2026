package com.example.lesson2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Xin chào! Đây là API đầu tiên của tôi";
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        Map<String, String> data = new HashMap<>();
        data.put("club", "HIT - Spring Boot");
        data.put("lesson", "Buổi 2");
        data.put("topic", "HTTP, Controller & Bean");
        return data;
    }
}