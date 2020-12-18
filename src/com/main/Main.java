package com.main;

<<<<<<< HEAD
import com.bean.Channel;
import com.bean.ResponseMessage;
import com.bean.User;
import com.bean.UserChannel;
import com.dao.concret.ChannelDAO;
import com.dao.concret.UserChannelDAO;
import com.dao.concret.UserDAO;

<<<<<<< HEAD
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

=======
>>>>>>> yoann
public class Main {

    public static void main(String[] args) {

<<<<<<< HEAD
            default:
                System.out.println("No such choice");
=======
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

>>>>>>> dev-paulius
        }

=======
>>>>>>> yoann
    }
}
