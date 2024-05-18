package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UnhandledException {
    
    @PostMapping("/exception")
    public Object postMethodName(@RequestBody String entity) {
        Long num1 = 1L;
        Long num2 = 1L;
        
        return num1/num2;
    }
    
}
