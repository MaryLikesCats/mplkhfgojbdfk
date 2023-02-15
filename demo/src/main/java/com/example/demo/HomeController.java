package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //provides a default message at localhost:8080
    @RequestMapping("/")
    public String defaultInstructions() {
        return "WEATHER SENSOR REST API";
    }
}
