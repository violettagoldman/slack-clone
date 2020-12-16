package com.main;

import com.controllers.Controller;
import com.controllers.UserController;
import com.models.ResponseMessage;
import com.models.User;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/slack?" +
                    "user=slack&password=123");
            //on set la connection dans la classe mère pour que tout les controlleurs puissent acceder à Connection
            Controller.setConnexion(conn);
            User user = new User("JohnSmith3","john@smith3.com","123");
            ResponseMessage<User> res =  UserController.createUser(user);
            System.out.println(res);
        } catch (Exception e){

        }

    }
}
