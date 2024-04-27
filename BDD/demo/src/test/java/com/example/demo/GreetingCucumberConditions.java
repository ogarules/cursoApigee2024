package com.example.demo;

import javax.management.ObjectName;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.models.Greeting;
import com.example.demo.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

@Slf4j
public class GreetingCucumberConditions {
    
    @Autowired
    GreetingContext greetingContext;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository repository;

    @Given("^Tenemos un usuario con id '(.+)'$")
    public void userWithId(Long id){
        greetingContext.setId(id);
        greetingContext.setUserName(repository.findById(id).getName());
    }

    @When("^el usuario desea ser saludado$")
    public void userWantsToBeGreeted() throws UnsupportedEncodingException, Exception{
        String result = mvc.perform(get("/hello/" + greetingContext.getId()))
                           .andExpect(status().isOk())
                           .andExpect(content().contentTypeCompatibleWith("application/json"))
                           .andDo(print())
                           //.andExpect(jsonPath("$.message", is("Hello oga")))
                           .andReturn().getResponse().getContentAsString();

        log.info(result);
        greetingContext.setGreeting(objectMapper.readValue(result, Greeting.class));

        //Assert.assertNotNull(result, "El saludo no debiera estar vacio");
    }
}
