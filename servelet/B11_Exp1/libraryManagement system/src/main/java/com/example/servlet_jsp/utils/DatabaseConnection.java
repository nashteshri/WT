package com.example.servlet_jsp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "root@123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println(" Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failure.");
            e.printStackTrace();
        }

        throw new SQLException("Connection failure.");
    }
}