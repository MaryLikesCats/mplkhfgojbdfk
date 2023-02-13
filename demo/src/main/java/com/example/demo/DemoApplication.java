package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws SQLException, IOException {

		int uuid = 100;
		Connection conn = DriverManager.getConnection("jdbc:sqlite:weatherSensorDB.db");
		SpringApplication.run(DemoApplication.class, args);
	}

	public static boolean dbSetup() throws SQLException {
		Database db = new Database();

		return true;
	}

}
