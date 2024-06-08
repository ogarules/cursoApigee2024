package com.example.demo.services;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestTemplateService {
    
    private final RestTemplate template;

    @CircuitBreaker(name = "botService", fallbackMethod = "fallBackTemplte")
    public String getTemplateResponse(){
        URI uri = URI.create("http://localhost:8091/dummy");

        String response = this.template.getForObject(uri, String.class);

        return response;
    }

    public String fallBackTemplte(Exception e){
        return "Errrroooooooooooorrrr";
    }
}
