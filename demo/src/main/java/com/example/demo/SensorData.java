package com.example.demo;

public class SensorData {
    private int id;
    private int temp;
    private int humidity;
    private int windSpeed;
    private String date;

    public SensorData(int id, int temp, int humidity, int windSpeed, String date){
        this.id = id;
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
