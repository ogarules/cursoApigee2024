package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Then;

public class GReetingCucumberValidations {
    @Autowired
    GreetingContext greetingContext;

    @Then("^el saludo solicitado '(.+)' es retornado$")
    public void greetingIsCorrect(String greet){
        assertEquals(greet, greetingContext.getGreeting().getMessage());
    }
}
