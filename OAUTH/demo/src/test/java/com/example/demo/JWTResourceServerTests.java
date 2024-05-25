package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.example.demo.models.Foo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@SpringBootTest
public class JWTResourceServerTests {
    
    private static String redirectUrl = "http://localhost:8080";
    private static String authorizeUrlPattern = "http://localhost:8089/realms/baeldung/protocol/openid-connect/auth?response_type=code&client_id=fooClient&scope=%s&redirect_uri=" + redirectUrl;
    private static String tokenUrl = "http://localhost:8089/realms/baeldung/protocol/openid-connect/token";
    private static String respurceUrl = "http://localhost:8080/foos";


    @Test
    public void testGetFoosSucces(){

        String accessToken = obtainAccessToken("read");

        Response response = RestAssured.given()
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
          .get(respurceUrl);

        String content = response.asString();
        var status = response.statusCode();

        assertThat(response.as(List.class)).hasSizeGreaterThan(0);
    }

    @Test
    public void testAddFoosSucces(){

        String accessToken = obtainAccessToken("read");
        var foo = new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));

        Response response = RestAssured.given()
          .contentType(ContentType.JSON)
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
          .body(foo)
          .log()
          .all()
          .post(respurceUrl);

        String content = response.asString();
        var status = response.statusCode();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


    private String obtainAccessToken(String scopes){
        Response response = RestAssured.given()
        .redirects()
        .follow(false)
        .get(String.format(authorizeUrlPattern, scopes));

        String authSessionId = response.getCookie("AUTH_SESSION_ID");
        String portAuthenticationUrl = response.asString()
                 .split("action=\"")[1].split("\"")[0]
                 .replace("&amp;", "&");
        
        response = RestAssured.given()
                   .redirects()
                   .follow(false)
                   .cookie("AUTH_SESSION_ID", authSessionId)
                   .formParams("username", "john@test.com", "password","123","credentialId", "")
                   .post(portAuthenticationUrl);
        
        assertThat(HttpStatus.FOUND.value()).isEqualTo(response.getStatusCode());

        String location = response.getHeader(HttpHeaders.LOCATION);
        String code = location.split("code=")[1].split("&")[0];

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("client_id", "fooClient");
        params.put("redirect_uri", redirectUrl);
        params.put("client_secret", "IBrX4WrfCTEn4ci0DVXSBhFbASADSdUQ");

        response = RestAssured.given()
                   .formParams(params)
                   .post(tokenUrl);

        return response.jsonPath()
        .getString("access_token");

    }
}
