package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SensorController {

    private SensorService sensorService;
    @Autowired
    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }
    @GetMapping("/sensor")
    public Sensor getSensor(@RequestParam Integer id){
        Optional sensor = sensorService.getService(id);
        if(sensor.isPresent()){
            return (Sensor) sensor.get();
        }
        return null;
    }

    @GetMapping("/sensorByLocation")
    public Sensor getSensorByLocation(@RequestParam String country){
        Optional sensor = sensorService.getServiceByLocation(country);
        if(sensor.isPresent()){
            return (Sensor) sensor.get();
        }
        return null;
    }
}
