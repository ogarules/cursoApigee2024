package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/echo")
public class HelloController {
    
    @GetMapping()
    public String getGreeting() {
        return "Hola";
    }
    
}
