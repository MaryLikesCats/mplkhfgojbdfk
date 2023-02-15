package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
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

    //Get all sensor readings
    @GetMapping("/sensor/data")
    public ArrayList getAllSensorReadings() throws SQLException {
        SensorData sensorData = new SensorData();
        return sensorData.getAllSensorReadings();
    }

    //Get all sensor readings for 1 sensor by passing it the sensor uuid
    @GetMapping("/sensor/data/{id}")
    public ArrayList getSensorDataById(@PathVariable Integer id) throws Exception {
        SensorData sensorData = new SensorData();
        return sensorData.getSensorReadingsById(id);
    }

    //Average Temperature
    @GetMapping("/sensor/data/temperature/{id}")
    public int getAverageTempById(@PathVariable Integer id) throws Exception {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageTempById(id);
    }

    //Get average metric for a sensor. Endpoint that takes in two request params. Id and Metric. The metric value can be Temperature, Humidity or WindSpeed.
    @GetMapping("/sensor/data/average")
    @ResponseBody
    public int getAverageMetricByIdAndMetric(@RequestParam Integer id, @RequestParam String metric) throws SQLException {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageMetricById(id, metric);
    }

    @GetMapping("/sensor/data/average/date")
    @ResponseBody
    public int getAverageMetricByIdAndMetricAndDate(@RequestParam Integer id, @RequestParam String metric, @RequestParam String date) throws SQLException {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageMetricByIdAndDate(id, metric, date);
    }

    @GetMapping("/sensor/data/average/date-range")
    @ResponseBody
    public int getAverageMetricByIdAndMetricAndDateRange(@RequestParam Integer id, @RequestParam String metric, @RequestParam String firstDate, @RequestParam String lastDate) throws SQLException, ParseException {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageMetricByIdAndDateRange(id, metric, firstDate, lastDate);
    }
//
//    @GetMapping("/sensor/data/{id}/{metric}")
//    public int getAverageMetricById(@PathVariable Integer id, String metric) throws Exception {
//        SensorData sensorData = new SensorData();
//        System.out.println(metric);
//        return sensorData.getAverageMetricById(id, metric);
//    }

    @PostMapping("/sensor/data/create")
    public void createSensorData(@RequestBody SensorData sensorData) throws SQLException {
        SensorData postSensorData = new SensorData();
        postSensorData.postNewSensorData(sensorData);
    }

}
