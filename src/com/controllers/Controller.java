package com.controllers;

import java.sql.Connection;

public class Controller {
    protected static Connection connexion;

    protected Controller() { }

    public static void setConnexion(Connection connexion) {
        Controller.connexion = connexion;
    }

}
