package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtTokenUtil;
import com.example.demo.models.AuthRequest;
import com.example.demo.models.User;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody AuthRequest request) {
        try{
            Authentication authenticate = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));

            User user = (User)authenticate.getPrincipal();

            return ResponseEntity.ok()
                   .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(user))
                   .body(user);
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
}
