package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.DummyService;
import com.example.demo.services.RestTemplateService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("dummy")
@RequiredArgsConstructor
public class DummyController {
    
    private final DummyService dummyService;
    private final RestTemplateService templateService;

    @GetMapping
    public String getDummyWithFeignClient() {
        return dummyService.getDummyFromFirstService();
    }
    
    @GetMapping("template")
    public String getDummyFromTemplate(){
        return this.templateService.getTemplateResponse();
    }
}
