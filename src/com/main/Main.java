package com.main;

import com.bean.ResponseMessage;
import com.bean.User;
import com.dao.concret.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        /*
        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();

        System.out.println("1- Sign Up");
        System.out.println("2- Sign In");

        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Username : ");
                String usernameSU = sc.nextLine();
                System.out.println("Email : ");
                String emailSU = sc.nextLine();
                System.out.println("Password : ");
                String passSU = sc.nextLine();

                User userSU = new User(1212,usernameSU,emailSU,passSU);

                System.out.println(userDAO.create(userSU).getMessage());
                break;

            case "2":
                System.out.println("Username : ");
                String usernameSI = sc.nextLine();
                System.out.println("Password : ");
                String passSI = sc.nextLine();

                User userSI = userDAO.signIn(usernameSI,passSI).getData();

                System.out.println(userSI);
                break;

            default:
                System.out.println("No such choice");
        }
         */

        UserDAO udao = new UserDAO();
        List<User> users = udao.findAll().getData();
        for(int i = 0 ; i < users.size() ; i++) {
            System.out.println(users.get(i));
        }

    }
}
