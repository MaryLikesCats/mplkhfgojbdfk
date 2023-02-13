package com.example.demo;

import java.sql.*;

public class Sensor {
    private int id;
    private String countryName;
    private String cityName;
    private Connection conn;
    private String databaseName;

//Model?
    public Sensor() throws SQLException {
        this.databaseName = "weatherDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }
    public Sensor(int id, String countryName, String cityName) {
        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public void getSensors() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from SensorReading");
        System.out.println(results);
//        return (Sensor) results;

    }

//    public Sensor getSensorById(int id) throws SQLException {
//        //open conn to db
//        //send sql request
//        Statement st = conn.createStatement();
//        ResultSet results = st.executeQuery("select * from SensorReading where uuid = "+id);
//        return (Sensor) results;
//
//
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
