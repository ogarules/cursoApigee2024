package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RestController
public class EchoController {
    
    @GetMapping("/echo")
    public String getMethodName(@RequestParam String message) {
        log.info("Echo {} => {}", message, new Date());
        return message;
    }
    
}
