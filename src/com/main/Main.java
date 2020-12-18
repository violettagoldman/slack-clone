package com.main;

import com.bean.Channel;
import com.bean.ResponseMessage;
import com.bean.User;
import com.bean.UserChannel;
import com.controller.UserController;
/*import com.dao.concret.ChannelDAO;
import com.dao.concret.UserChannelDAO;*/
import com.dao.concret.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {

        //.SQLIntegrityConstraintViolationException


        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        UserController userController = new UserController();
/*        ChannelDAO channelDAO = new ChannelDAO();
        UserChannelDAO userChannelDAO = new UserChannelDAO();*/

        String choice = "";

        while (!choice.equals("0")) {

            System.out.println();

            System.out.println("1- Sign Up");
            System.out.println("2- Sign In");
            System.out.println("3- List User");
/*          System.out.println("4- List Channel");
            System.out.println("5- Create Channel");
            System.out.println("6- Add User In A Channel");
            System.out.println("7- List Of All User In A Channel");*/
            System.out.println("0- Quit");

            System.out.println();

            // USER MAKES CHOICE
            System.out.println("Choice :");
            choice = sc.nextLine();

            // SYSTEM READ CHOICE
            switch (choice) {
                case "1":   // SIGN UP

                    Optional opUp = userController.signUp();

                    if (opUp.isPresent()) {
                        System.out.println("See your informations :");
                        System.out.println(opUp.get());
                    } else {
                        System.out.println("Error in creation");
                    }

                    break;

                case "2":   // SIGN IN

                    Optional opIn = userController.signIn();

                    if (opIn.isPresent()) {
                        System.out.println("See your informations :");
                        System.out.println(opIn.get());
                    } else {
                        System.out.println("Error in connection");
                    }

                    break;

                case "3":   // LIST USERS
                    UserDAO udao = new UserDAO();

                    List<User> users = udao.findAll().get();
                    for(int i = 0 ; i < users.size() ; i++) {
                        System.out.println(users.get(i));
                    }
                    break;

/*              case "4":
                    ChannelDAO cdao = new ChannelDAO();

                    List<Channel> channels = channelDAO.findAll().getData();
                    for(int i = 0 ; i < channels.size() ; i++) {
                      System.out.println(channels.get(i));
                    }
                    break;

                case "5":
                    System.out.println("Nom du channel : ");
                    String channelNameTest= sc.nextLine();
                    System.out.println("User admin id : ");
                    long channelAdminTest = Long.parseLong(sc.nextLine());

                    Channel channelTest = new Channel(11, channelAdminTest,channelNameTest);

                    System.out.println(channelDAO.create(channelTest).getMessage());
                    break;

                case "6":

                    System.out.println("Id du user : ");
                    long userChannelId = Long.parseLong(sc.nextLine());
                    System.out.println("Id du channel: ");
                    long channelId = Long.parseLong(sc.nextLine());

                    UserChannel userChannelTest = new UserChannel(11, channelId, userChannelId);

                    System.out.println(userChannelDAO.create(userChannelTest).getMessage());
                    break;

                case "7":
                    UserChannelDAO ucdao = new UserChannelDAO();

                    System.out.println("Id du channel : ");
                    long channelIdList = Long.parseLong(sc.nextLine());

                    List<UserChannel> userChannels = (List<UserChannel>) userChannelDAO.findAll(channelIdList).getData();
                    for(int i = 0 ; i < userChannels.size() ; i++) {
                        System.out.println(userChannels.get(i));
                    }
                    break;*/

                case "0":
                    System.out.println("Goodbye !");
                    break;

                default:
                    System.out.println("No such choice");
            }
        }
    }
}
