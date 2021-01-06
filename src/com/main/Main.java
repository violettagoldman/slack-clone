package com.main;

import com.controllers.UserController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);

        UserController userController = new UserController();

        System.out.println("Username :");
        String us = sc.nextLine();

        System.out.println("Email :");
        String email = sc.nextLine();

        System.out.println("Password :");
        String pass = sc.nextLine();

        System.out.println(userController.signUp(us,email,pass).getData());

    }
}
