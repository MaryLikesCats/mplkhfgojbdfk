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

    @BeforeEach
    void setUp() throws SQLException {
        sensor = new SensorController();
        statement = mock(Statement.class);
        sensor.conn = mock(Connection.class);
        when(sensor.conn.createStatement()).thenReturn(statement);
    }

    @Test
    void getAllSensors() {


    }

    @Test
    void getSensorsById() throws Exception {
        HashMap testSensor = new HashMap();
        testSensor.put("uuid", 1);
        testSensor.put("country", "Belgium");
        testSensor.put("city", "Brussels");
        insertSensorIntoDatabase(testSensor);

        HashMap returnedSensor = new HashMap();
        returnedSensor = getSensorsById(1);

        assertEquals(testSensor, returnedSensor);

    }

    @Test
    void postNewSensor() {
    }

    @Test
    void saveSensor() {
    }

    public void insertSensorIntoDatabase(HashMap sensor) throws Exception {
        String databaseName = "weatherSensorDB.db";
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        String query = "insert into Sensor (uuid, country, city) values (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, (int) sensor.get("uuid"));
            stmt.setString(2, (String) sensor.get("country"));
            stmt.setString(3, (String) sensor.get("city"));
            stmt.executeUpdate();
        }
    }
}