package com.example.demo;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;



    public class Sensor {
    private int uuid;
    private String country;
    private String city;
    private Connection conn;
    private String databaseName;

    //Model?
    public Sensor() throws SQLException {
        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }

    public Sensor(int uuid, String country, String city) {
        this.uuid = uuid;
        this.country = country;
        this.city = city;
    }

    public ArrayList<HashMap> getAllSensors() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from Sensor");

        ResultSetMetaData rsmd = results.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        ArrayList<HashMap> ArrayListOfHashMaps = new ArrayList<>();

        while (results.next()) {
            HashMap newHashmapForEachLoop = new HashMap();
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = results.getString(i);
                newHashmapForEachLoop.put(rsmd.getColumnName(i), columnValue);
            }
            ArrayListOfHashMaps.add(newHashmapForEachLoop);
        }
        return ArrayListOfHashMaps;


    }

    public HashMap getSensorsById(int id) throws Exception {
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from Sensor where uuid = " + id);

        HashMap returnedSensor = new HashMap();
        ResultSetMetaData rsmd = results.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (results.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = results.getString(i);
                returnedSensor.put(rsmd.getColumnName(i), columnValue);
            }
        }
        return returnedSensor;


    }


    public void postNewSensor(Sensor sensor) throws SQLException {
        String request = "insert into Sensor values ('" + sensor.uuid + "', '" + sensor.country + "', '" + sensor.city + "')";
        Statement statement = conn.createStatement();
        statement.executeUpdate(request);
    }


    public Sensor saveSensor(Sensor sensor){
        return null;
    }
    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}




