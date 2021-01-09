package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

    private static final String URL = "jdbc:mysql://localhost/slack";
    private static final String USER = "root";
    private static final String PASSWRD = "1253";
    //private static final String USER = "user";
    //private static final String PASSWRD = "Pass1234!";
    private static Connection connect;

    //Return instance and create if not existing
    public static Connection getInstance() {
        if(connect == null) {
            try {
                connect = DriverManager.getConnection(URL, USER, PASSWRD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
