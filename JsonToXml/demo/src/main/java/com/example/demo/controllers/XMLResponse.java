package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.DataResponse;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
public class XMLResponse {
    
    @PostMapping("/xml")
    public Object postMethodName(HttpServletRequest request) throws IOException {
        byte[] requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        log.info("request body => {}", new String(requestBody, StandardCharsets.UTF_8));
        
        return DataResponse.builder().value("123").build();
    }
    
}
