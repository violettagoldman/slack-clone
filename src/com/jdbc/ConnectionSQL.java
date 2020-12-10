package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

    private static String URL = "jdbc:mysql://localhost/slack";
    private static String USER = "root";
    private static String PASSWRD = "root";

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
