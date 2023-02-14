package com.example.demo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection conn;
    private String databaseName;

    public Database() throws SQLException {
        this.databaseName = "weatherSensorDB.db";
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

    }

    //This function is called from the main to initially create the Database
    public boolean createDatabase(){

        File file = new File(databaseName);
//        if(file.exists()){
//            System.out.println("This database exists");
//        }

        //Creates the database and table for Sensor and SensorData
        String sql = "create table Sensor(uuid INTEGER PRIMARY KEY, Country TEXT, City TEXT)";
        String sql2 = "create table SensorReading(Temperature INTEGER, Humidity INTEGER, WindSpeed INTEGER, Date TEXT, uuid INTEGER, FOREIGN KEY (uuid) REFERENCES Sensor(uuid) ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION)";


        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean populateDatabase() throws SQLException {

        // inserts 5 new sensors into Sensor table
        String italy = "insert into Sensor values ('100', 'Italy', 'Rome')";
        String japan = "insert into Sensor values ('101', 'Japan', 'Tokyo')";
        String ireland = "insert into Sensor values ('102', 'Ireland', 'Dublin')";
        String france = "insert into Sensor values ('103', 'France', 'Paris')";
        String usa = "insert into Sensor values ('104', 'America', 'Washington DC')";

        Statement statement = conn.createStatement();
        statement.executeUpdate(italy);
        statement.executeUpdate(japan);
        statement.executeUpdate(ireland);
        statement.executeUpdate(france);
        statement.executeUpdate(usa);

        // inserts 6 new sensor readings into SensorReading table
        String s1 = "insert into SensorReading values ('36', '40', '22', '11/01/2023', '100' )";
        String s2 = "insert into SensorReading values ('31', '45', '10', '12/01/2023', '100' )";
        String s3 = "insert into SensorReading values ('29', '61', '15', '13/01/2023', '100' )";
        String s4 = "insert into SensorReading values ('21', '39', '38', '11/01/2023', '101' )";
        String s5 = "insert into SensorReading values ('19', '35', '32', '12/01/2023', '101' )";
        String s6 = "insert into SensorReading values ('20', '33', '41', '13/01/2023', '101' )";
        String s7 = "insert into SensorReading values ('10', '10', '11', '11/01/2023', '102' )";
        String s8 = "insert into SensorReading values ('21', '22', '23', '12/01/2023', '102' )";
        String s9 = "insert into SensorReading values ('29', '50', '40', '11/01/2023', '103' )";
        String s10 = "insert into SensorReading values ('28', '2', '3', '13/01/2023', '103' )";
        String s11 = "insert into SensorReading values ('70', '34', '1', '12/01/2023', '104' )";
        String s12 = "insert into SensorReading values ('65', '16', '75', '13/01/2023', '104' )";

        statement.executeUpdate(s1);
        statement.executeUpdate(s2);
        statement.executeUpdate(s3);
        statement.executeUpdate(s4);
        statement.executeUpdate(s5);
        statement.executeUpdate(s6);
        statement.executeUpdate(s7);
        statement.executeUpdate(s8);
        statement.executeUpdate(s9);
        statement.executeUpdate(s10);
        statement.executeUpdate(s11);
        statement.executeUpdate(s12);

        return true;
    }
}