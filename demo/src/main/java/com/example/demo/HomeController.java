package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String defaultInstructions() {
        return "WEATHER SENSER REST API" + "\n" + "---------------- \n to query by id use this /sensor/id ";
    }
}
