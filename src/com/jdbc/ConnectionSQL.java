package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

    private static final String URL = "jdbc:mysql://135.181.151.73:3306/slack";
    private static final String USER = "root";
    private static final String PASSWRD = "1234";

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
