package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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

    @RequestMapping("/")
    public String defaultInstructions() {
        return "WEATHER SENSER REST API" + "\n" + "---------------- \n to query by id use this /sensor/id ";
    }

    private SensorService sensorService;
    @Autowired
    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }
    @GetMapping("/sensor")
    public ArrayList getAllSensor() throws SQLException {
        Sensor s = new Sensor();
//        s.getSensors();
        return s.getAllSensors();

    }

    @GetMapping("/sensor/{id}")
    public HashMap getSensorById(@PathVariable Integer id) throws Exception {
        Sensor s = new Sensor();
        return s.getSensorsById(id);
    }


    @GetMapping("/sensorByLocation")
    public Sensor getSensorByLocation(@RequestParam String country){
        Optional sensor = sensorService.getServiceByLocation(country);
        if(sensor.isPresent()){
            return (Sensor) sensor.get();
        }
        return null;
    }

//    @PostMapping("/employees")
//    Employee newEmployee(@RequestBody Employee newEmployee) {
//        return repository.save(newEmployee);
//    }


}
