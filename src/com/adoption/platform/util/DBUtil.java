package com.adoption.platform.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // --- CONFIGURE THESE FOR YOUR DATABASE ---
    private static final String URL = "jdbc:mysql://localhost:3306/pet_adoption_db"; 
    private static final String USER = "root";       
    private static final String PASS = "(Happy@3168)"; 

    // The method that every DAO will call to get a database connection
    public static Connection getConnection() throws SQLException {
        // This is the core JDBC connection call.
        try {
        // Explicitly load the driver class
        Class.forName("com.mysql.cj.jdbc.Driver"); 
    } catch (ClassNotFoundException e) {
        throw new SQLException("MySQL JDBC Driver not found.", e);
    }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}