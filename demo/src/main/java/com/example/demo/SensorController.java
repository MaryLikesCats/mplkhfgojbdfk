package com.example.demo;

import org.jooq.impl.DSL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;

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
    public HashMap getAllSensor() throws SQLException {
        System.out.println("HI");
        Sensor s = new Sensor();
        HashMap r = s.getAllSensors();
//        s.getSensors();
        return r;

    }

    @GetMapping("/sensor/{id}")
    public JSONArray getSensorById(@PathVariable Integer id) throws Exception {
        System.out.println("SensorById - Sensor Controller");
        Sensor s = new Sensor();
//        HashMap r = s.getSensorsById(id);
        System.out.println(s.getSensorsById(id));
        return s.getSensorsById(id);
    }



//    public Sensor getSensor(@PathVariable Integer id) throws SQLException {
//        return Sensor.getSensorById(id);
//    }

    //To update to above when working
//    public Sensor getSensor(@RequestParam Integer id){
//        Optional sensor = sensorService.getService(id);
//        if(sensor.isPresent()){
//            return (Sensor) sensor.get();
//        }
//        return null;
//    }

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
