package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

            HashMap resp = new HashMap();
            ResultSetMetaData rsmd = results.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = results.getString(i);
                    resp.put(rsmd.getColumnName(i), columnValue);
                }
            }
            return resp;

        }


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




