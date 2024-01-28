package com.example.demoapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAPI {
    @GetMapping("/hello/{friend}")
    public String hello(@PathVariable("friend") String friend) {
        return "Hello " + friend + "!";
        // Hello world!, Hello Gerald!
    }
}
