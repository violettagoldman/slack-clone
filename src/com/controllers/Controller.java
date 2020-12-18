package com.controllers;

import com.jdbc.ConnectionSQL;

import java.sql.Connection;

public abstract class Controller {
    public Connection connect = ConnectionSQL.getInstance();
}
