package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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

//    @RequestMapping("/")
//    public String defaultInstructions() {
//        return "WEATHER SENSER REST API" + "\n" + "---------------- \n to query by id use this /sensor/id ";
//    }

    private SensorService sensorService;
    @Autowired
    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }
    @GetMapping("/sensor")
    public ArrayList getAllSensor() throws SQLException {
        Sensor sensor = new Sensor();
        return sensor.getAllSensors();

    }

    @GetMapping("/sensor/{id}")
    public HashMap getSensorById(@PathVariable Integer id) throws Exception {
        Sensor sensor = new Sensor();
        return sensor.getSensorsById(id);
    }

// To implement
//    @GetMapping("/sensorByLocation")
//    public Sensor getSensorByLocation(@RequestParam String country){
//        Optional sensor = sensorService.getServiceByLocation(country);
//        if(sensor.isPresent()){
//            return (Sensor) sensor.get();
//        }
//        return null;
//    }

//    @PostMapping("/sensor/create")
//    public ResponseEntity<Sensor> newSensor(@RequestBody Sensor newSensor) {
//        return new ResponseEntity<Sensor>(Sensor.saveSensor(newSensor), HttpStatus.CREATED);
//    }

    @PostMapping("/sensor/create")
    public void createSensor(@RequestBody Sensor sensor) throws SQLException {
        Sensor postSensor = new Sensor();
        postSensor.postNewSensor(sensor);
    }


}















