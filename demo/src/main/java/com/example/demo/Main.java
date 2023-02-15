package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws SQLException, IOException {

		Connection conn = DriverManager.getConnection("jdbc:sqlite:weatherSensorDB.db");
//		dbSetup();
		SpringApplication.run(Main.class, args);
	}

	public static boolean dbSetup() throws SQLException {
		Database db = new Database();
		db.createDatabase();
		db.populateDatabase();

		return true;
	}

}
