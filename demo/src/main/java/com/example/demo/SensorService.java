package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private List<Sensor> sensorList;

    public SensorService() {
        //put in db connection here and get the sensor table
        sensorList = new ArrayList<>();
        Sensor sensor = new Sensor(1, "USA", "NEW YORK");
        Sensor sensor2 = new Sensor(2, "ENG", "LONDON");
        Sensor sensor3 = new Sensor(3, "IRE", "GALWAY");
        Sensor sensor4 = new Sensor(4, "FRA", "PARIS");
        Sensor sensor5 = new Sensor(5, "GER", "BERLIN");
        sensorList.addAll(Arrays.asList(sensor,sensor2,sensor3,sensor4,sensor5));
    }

    //GET REQUEST BY ID
    //Make it so that it uses the database
    public Optional<Sensor> getService(Integer id) {
        Optional optional = Optional.empty();
        for(Sensor sensor: sensorList){
            if(id ==sensor.getUuid()){
                optional = Optional.of(sensor);
                return optional;
            }
        }
        return optional;
    }

    //This doesn't work :(
    public Optional<Sensor> getServiceByLocation(String country) {
        Optional optional = Optional.empty();
        for(Sensor sensor: sensorList){
            if(country == sensor.getCountry()){
                optional = Optional.of(sensor);
                return optional;
            }
        }
        return optional;
    }
}
