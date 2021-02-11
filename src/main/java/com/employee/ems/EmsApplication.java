package com.employee.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

@SpringBootApplication
public class EmsApplication {

	public static void main(String[] args) {

		/*
		 * Testing the database Connection */
		String dbUrl = "jdbc:mysql://localhost:3306/db_employee?allowPublicKeyRetrieval=true&useSSL=false";
		try {
			System.out.println("Connecting to the Database ...");
			Connection connection = DriverManager.getConnection(dbUrl, "root", "osamakhan");
			System.out.println("Connected to the Database! ");
		} catch (Exception e) {
			System.out.println(
					e.getMessage()
			);
		}

		/*
		* Running the Application */
		SpringApplication.run(EmsApplication.class, args);
	}

}
