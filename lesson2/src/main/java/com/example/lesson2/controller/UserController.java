package com.example.lesson2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/api/user")
    public Map<String,String> info(){
        Map<String,String> data = new HashMap<>();
        data.put("name","Viet Doanh");
        data.put("email","vietdoanh0502@gmail.com");
        return data;
    }

}
