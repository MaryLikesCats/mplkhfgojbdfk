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

    public HashMap getSensors() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from Sensor");
        System.out.println("Hello");
//        return results;
        HashMap resp = new HashMap();
        ResultSetMetaData rsmd = results.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (results.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = results.getString(i);
                resp.put(columnValue, rsmd.getColumnName(i));
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
        return resp;



//        ResultSetMetaData rsmd = results.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        while (results.next()) {
//            for (int i = 1; i <= columnsNumber; i++) {
//                if (i > 1) System.out.print(",  ");
//                String columnValue = results.getString(i);
//                System.out.print(columnValue + " " + rsmd.getColumnName(i));
//            }
//            System.out.println("");
//        }
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
