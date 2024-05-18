package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping()
    public String getUser() {
        return "Hello user";
    }
    
}
