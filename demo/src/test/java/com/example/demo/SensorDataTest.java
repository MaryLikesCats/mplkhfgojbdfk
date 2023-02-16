package com.example.demo;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SensorDataTest {

    @Test
    void postNewSensorData() throws SQLException {
        SensorData sensor = new SensorData(65, 16, 75, 104, "13/01/2023");
        Boolean success = sensor.postNewSensorData(sensor).get("Success");
        assertEquals(true, success);
    }
}