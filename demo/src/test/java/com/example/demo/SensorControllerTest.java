package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SensorControllerTest {

    // For this test and the tests in SenorDataControllerTest, I am getting this error which I cannot work out myself
    // org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Sensor)
    // I feel I'm on the right lines, I just can't get past this. And once I figure the error out, I should be able to provide better test coverage.
    @Test
    void getSensorById() throws Exception {
        HashMap mockHashMap = new HashMap();
        mockHashMap.put("uuid", 101);
        mockHashMap.put("country", "Japan");
        mockHashMap.put("city", "Tokyo");

        Sensor mockS = Mockito.mock(Sensor.class);
        when(mockS.getSensorsById(101)).thenReturn(mockHashMap);
        SensorController sensorController = new SensorController();

        assertEquals(101, sensorController.getSensorById(101).get("uuid"));
        assertEquals("Japan", sensorController.getSensorById(101).get("country"));
        assertEquals("Tokyo", sensorController.getSensorById(101).get("city"));
    }
}