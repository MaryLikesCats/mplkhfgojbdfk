package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class SensorController {
    private String databaseName;
    private Connection conn;

    public SensorController() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }

    //gets all sensors from the database
    @GetMapping("/sensor")
    public ArrayList getAllSensor() throws SQLException {
        Sensor sensor = new Sensor();
        return sensor.getAllSensors();

    }
    //returns a sensor by id
    @GetMapping("/sensor/{id}")
    public HashMap getSensorById(@PathVariable Integer id) throws Exception {
        Sensor sensor = new Sensor();
        return sensor.getSensorsById(id);
    }

    //creates a new sensor in the database. You must include id, country and city in the request body.
    @PostMapping("/sensor/create")
    public void createSensor(@RequestBody Sensor sensor) throws SQLException {
        Sensor postSensor = new Sensor();
        postSensor.saveNewSensor(sensor);
    }
}















