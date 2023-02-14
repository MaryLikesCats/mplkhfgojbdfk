package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class SensorDataController {
    private String databaseName;
    private Connection conn;

    public SensorDataController() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }
    private SensorService sensorService;
    @Autowired
    public SensorDataController(SensorService sensorService){
        this.sensorService = sensorService;
    }

    @GetMapping("/sensor/data")
    public ArrayList getAllSensor() throws SQLException {
        SensorData sensorData = new SensorData();
        return sensorData.getAllSensorReadings();
    }

    @GetMapping("/sensor/data/{id}")
    public ArrayList getSensorById(@PathVariable Integer id) throws Exception {
        SensorData sensorData = new SensorData();
        return sensorData.getSensorReadingsById(id);
    }
}
