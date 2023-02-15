package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class SensorControllerTest {
    @Autowired
    MockMvc mockMvc;
//
//    @Test
//    void getAllSensor() throws Exception {
//
//        Sensor sensor = new Sensor(1, "Belgium","Brussels");
//        Sensor sensor2 = new Sensor(1, "Norway","Oslo");
//        Sensor sensor3 = new Sensor(1, "Portugal","Lisbon");
//        String inputToJSON = this.map
//        mockMvc.perform(MockMvcRequestBuilders.get("/sensor")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void getSensorById() {
//    }

    @Test
    void createSensor() {
        //test the sensor is successfully saved




    }
}