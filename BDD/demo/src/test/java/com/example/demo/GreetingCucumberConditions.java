package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingCucumberConditions {
    
    @Autowired
    GreetingContext greetingContext;

    @Given("^Tenemos un usuario con id '(.+)'$")
    public void userWithId(Long id){
        greetingContext.setId(id);
    }

    @When("^el usuario desea ser saludado$")
    public void userWantsToBeGreeted(){
        
    }
}
