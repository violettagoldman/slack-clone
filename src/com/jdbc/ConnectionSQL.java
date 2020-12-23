package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

    private static final String URL = "jdbc:mysql://localhost/slack";
    private static final String USER = "root";
    private static final String PASSWRD = "root";

    private static final Connection connect = create();


    //Return instance and create if not existing
    private static Connection create() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWRD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getInstance(){
        return connect;
    }
}
