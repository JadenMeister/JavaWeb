package com.example.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
    
    @GetMapping("/hello/json")
    public HelloResponse helloJson() {
        return new HelloResponse("Hello", "World from Spring Boot!");
    }
    
    // 간단한 응답 클래스
    public static class HelloResponse {
        private String greeting;
        private String message;
        
        public HelloResponse(String greeting, String message) {
            this.greeting = greeting;
            this.message = message;
        }
        
        public String getGreeting() {
            return greeting;
        }
        
        public String getMessage() {
            return message;
        }
    }
}
