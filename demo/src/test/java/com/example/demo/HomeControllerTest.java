package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void defaultInstructions() {
        HomeController homeController = new HomeController();
        String response = homeController.defaultInstructions();
        assertEquals("WEATHER SENSOR REST API", response);
    }

}