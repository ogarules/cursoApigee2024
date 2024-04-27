package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Greeting message", requiredProperties = {"message"})
public class Greeting {

    @Schema(example = "Hello World", description = "Greeting message")
    private String message;

}
