package com.example.demo;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class SensorData {
    private int uuid;
    private int temp;
    private int humidity;
    private int windSpeed;
    private String date;
    private Connection conn;
    private String databaseName;

    public SensorData(int temp, int humidity, int windSpeed, int uuid, String date) throws SQLException {
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.date = date;
        this.uuid = uuid;
        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }
    public SensorData() throws SQLException {
        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

    }

    public ArrayList getAllSensorReadings() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from SensorReading");

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

    public ArrayList getSensorReadingsById(Integer id) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from SensorReading where uuid = " + id);

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

    public int getAverageMetricById(Integer id, String metric) throws SQLException {
        int sumOfMetricValue = 0;
        ArrayList<HashMap> arrayListOfHashMapsOfSensorDataById = getSensorReadingsById(id);

        for (int i = 0; i < arrayListOfHashMapsOfSensorDataById.size(); i++) {
            HashMap<String, String> currentHashmap = arrayListOfHashMapsOfSensorDataById.get(i);

            SensorData sensorData = new SensorData(parseInt(currentHashmap.get("Temperature")), parseInt(currentHashmap.get("Humidity")), parseInt(currentHashmap.get("WindSpeed")), parseInt(currentHashmap.get("uuid")), currentHashmap.get("Date"));

            sumOfMetricValue = sumOfMetricValue + sensorData.getMetric(metric);
        }
        return sumOfMetricValue/arrayListOfHashMapsOfSensorDataById.size();

    }

    private int getMetric(String metric) {
        int returnValue;
        switch (metric) {

            case "Temperature":
                returnValue = temp;
                break;

            case "Humidity":
                returnValue = humidity;
                break;

            case "WindSpeed":
                returnValue = windSpeed;
                break;

            default:
                returnValue = 0;
        }
        return returnValue;
    }

    public HashMap<String, Boolean> postNewSensorData(SensorData sensorData) throws SQLException {
        HashMap<String, Boolean> ret = new HashMap();
        Statement st = conn.createStatement();
        ResultSet results = st.executeQuery("select * from SensorReading where uuid = " + sensorData.uuid);

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

        if (ArrayListOfHashMaps.size() > 1) {
            String request = "insert into SensorReading values ('" + sensorData.temp + "', '" + sensorData.humidity + "', '" + sensorData.windSpeed + "', '" + sensorData.date + "', '" + sensorData.uuid + " ')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(request);
            ret.put("Success", true);

        }
        else {
            ret.put("Sensor does not exist. Ensure you have the correct sensor id", false);
        }
        return ret;
    }

    public int getAverageMetricByIdAndDate(Integer id, String metric, String date) throws SQLException {

        int sumOfMetricValue = 0;
        int counter = 0;
        ArrayList<HashMap> arrayListOfHashMapsOfSensorDataById = getSensorReadingsById(id);

        for (int i = 0; i < arrayListOfHashMapsOfSensorDataById.size(); i++) {
            HashMap<String, String> currentHashmap = arrayListOfHashMapsOfSensorDataById.get(i);

            SensorData sensorData = new SensorData(parseInt(currentHashmap.get("Temperature")), parseInt(currentHashmap.get("Humidity")), parseInt(currentHashmap.get("WindSpeed")), parseInt(currentHashmap.get("uuid")), currentHashmap.get("Date"));

            if(currentHashmap.get("Date").equals(date)) {
                sumOfMetricValue = sumOfMetricValue + sensorData.getMetric(metric);
                counter++;
            }

        }
        return sumOfMetricValue/counter;

    }

    public int getAverageMetricByIdAndDateRange(Integer id, String metric, String firstDate, String lastDate) throws ParseException, SQLException {
        //convert all dates from string to datetype

        Date firstDateFormatted = convertStringToSimpleDateFormat(firstDate);
        Date lastDateFormatted = convertStringToSimpleDateFormat(lastDate);

        int sumOfMetricValue = 0;
        int counter = 0;
        ArrayList<HashMap> arrayListOfHashMapsOfSensorDataById = getSensorReadingsById(id);

        for (int i = 0; i < arrayListOfHashMapsOfSensorDataById.size(); i++) {

            HashMap<String, String> currentHashmap = arrayListOfHashMapsOfSensorDataById.get(i);

            SensorData sensorData = new SensorData(parseInt(currentHashmap.get("Temperature")), parseInt(currentHashmap.get("Humidity")), parseInt(currentHashmap.get("WindSpeed")), parseInt(currentHashmap.get("uuid")), currentHashmap.get("Date"));

            if(isWithinRange(convertStringToSimpleDateFormat(currentHashmap.get(("Date"))), firstDateFormatted, lastDateFormatted)){
                sumOfMetricValue = sumOfMetricValue + sensorData.getMetric(metric);
                counter++;
            }

        }
        return sumOfMetricValue/counter;
    }


    boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
        return !(testDate.before(startDate) || testDate.after(endDate));
    }

    public Date convertStringToSimpleDateFormat(String dateString) throws ParseException {
        Date dateFormatted=new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        return dateFormatted;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
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
