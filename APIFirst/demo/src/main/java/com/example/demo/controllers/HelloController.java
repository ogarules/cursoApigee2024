package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/hello")
@Tag(name="Greeting", description = "the Greeting API")
public class HelloController {
    
    @Operation(summary = "Get greeting for user ID", responses = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Greeting.class))),
        @ApiResponse(responseCode = "404", description = "Greeting not found")
    })
    @GetMapping("/{message}")
    public Greeting getMethodName(
    @Parameter(example = "Hola", name="message", description = "Message for the greeting")
    @PathVariable String message) {
        return Greeting.builder().message(message).build();
    }
    
}
