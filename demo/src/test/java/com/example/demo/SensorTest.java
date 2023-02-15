package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SensorTest {

    private Sensor sensor;
    private Statement statement;
    private String databaseName;
    private Connection conn;

    SensorTest() throws SQLException {
        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }

    @Test
    void testSave() throws SQLException {
        Sensor sensor = new Sensor(4, "Ireland", "Galway");
        Boolean success = sensor.saveNewSensor(sensor);
        assertEquals(true, success);
    }


}