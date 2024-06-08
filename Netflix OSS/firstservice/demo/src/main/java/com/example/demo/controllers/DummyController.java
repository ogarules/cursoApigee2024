package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("dummy")
public class DummyController {

    @Value("${server.port}")
    private String port;

    @GetMapping
    public String getPort() {
        return "dummy puerto => " + port;
    }
    
}
