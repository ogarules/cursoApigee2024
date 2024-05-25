package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Foo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
;

@Slf4j
@RestController
@RequestMapping("/foos")
public class FooController {
    
    @GetMapping("/{id}")
    public Foo getMethodName(@RequestParam Long id) {
        log.info("Getting foo => {}", id);
        return new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }
    
    @GetMapping
    public List<Foo> getMethodName(@RequestParam String param) {
        log.info("Getting all Foos");
        return Arrays.asList(
            new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4))
           ,new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4))
           ,new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4)));
    }
 
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void postMethodName(@RequestBody Foo entity) {
        log.info("Created => {}", entity);
    }
    
}
