package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests(auth -> auth
          .antMatchers(HttpMethod.GET, "/foos/**")
          .permitAll()
          .antMatchers(HttpMethod.POST, "/foos")
          .hasAuthority("SCOPE_write")
          .anyRequest()
          .authenticated())
          .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }
}
