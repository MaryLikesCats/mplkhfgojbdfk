package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SensorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Sensor sensor;

    @Test
    void getSensorById() throws Exception {
        HashMap mockHashMap = new HashMap();
        mockHashMap.put("uuid", 101);
        mockHashMap.put("country", "Japan");
        mockHashMap.put("city", "Tokyo");

        when(sensor.getSensorsById(101)).thenReturn(mockHashMap);

        mockMvc.perform(get("/sensor/101")).andExpect(status().isOk()).andExpect(content().string(equalTo(mockHashMap.toString())));
    }
}