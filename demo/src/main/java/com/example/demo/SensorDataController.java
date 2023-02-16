package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class SensorDataController {
    private String databaseName;
    private Connection conn;
    private SensorData sensorData;

    public SensorDataController(SensorData sensorData){
        this.sensorData = sensorData;
    }

    public SensorDataController() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
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

    //Get average metric for a sensor. Endpoint that takes in two request params. Id and Metric. The metric value can be Temperature, Humidity or WindSpeed.
    @GetMapping("/sensor/data/average")
    @ResponseBody
    public int getAverageMetricByIdAndMetric(@RequestParam Integer id, @RequestParam String metric) throws SQLException {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageMetricById(id, metric);
    }

    //Get average metric for a sensor, by id, on a specified date
    @GetMapping("/sensor/data/average/date")
    @ResponseBody
    public int getAverageMetricByIdAndMetricAndDate(@RequestParam Integer id, @RequestParam String metric, @RequestParam String date) throws SQLException {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageMetricByIdAndDate(id, metric, date);
    }

    //Get average metric for a sensor, by id, within a date range
    @GetMapping("/sensor/data/average/date-range")
    @ResponseBody
    public int getAverageMetricByIdAndMetricAndDateRange(@RequestParam Integer id, @RequestParam String metric, @RequestParam String firstDate, @RequestParam String lastDate) throws SQLException, ParseException {
        SensorData sensorData = new SensorData();
        return sensorData.getAverageMetricByIdAndDateRange(id, metric, firstDate, lastDate);
    }

    //create a new sensor reading in the database
    @PostMapping("/sensor/data/create")
    public String createSensorData(@RequestBody SensorData sensorData) throws SQLException {
        SensorData postSensorData = new SensorData();
        return (String) postSensorData.postNewSensorData(sensorData).keySet().toArray()[0];
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("Error: Mismatched types. Ensure id is a integer.");
        return message;
    }

}
