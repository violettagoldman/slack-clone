package com.main;

import com.bean.Channel;
import com.bean.ResponseMessage;
import com.bean.User;
import com.bean.UserChannel;
import com.controller.ChannelController;
import com.controller.UserChannelController;
import com.dao.concret.ChannelDAO;
import com.dao.concret.UserChannelDAO;
import com.dao.concret.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {


        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        ChannelDAO channelDAO = new ChannelDAO();
        UserChannelDAO userChannelDAO = new UserChannelDAO();

        ChannelController channelController = new ChannelController();

        System.out.println("1- Sign Up");
        System.out.println("2- Sign In");
        System.out.println("3- List User");
        System.out.println("4- List Channel");
        System.out.println("5- Create Channel");
        System.out.println("6- Add User In A Channel");
        System.out.println("7- List Of All User In A Channel");

        String choice = sc.nextLine();

        switch (choice) {

            case "1":
                System.out.println("Username : "); //test
                String usernameSU = sc.nextLine();
                System.out.println("Email : "); //test@test.com
                String emailSU = sc.nextLine();
                System.out.println("Password : "); //Az0Er1Ty2
                String passSU = sc.nextLine();

                User userSU = new User(1,usernameSU,emailSU,passSU);

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

            case "3":
                UserDAO udao = new UserDAO();

                List<User> users = udao.findAll().getData();
                for(int i = 0 ; i < users.size() ; i++) {
                    System.out.println(users.get(i));
                }
                break;

            case "4":
                ChannelController cdao = new ChannelController();

                Optional<List<Channel>> channels = cdao.findAllChannel();
                for(int i = 0 ; i < channels.stream().count() ; i++) {
                    System.out.println(channels.equals(i));
                }
                break;

            case "5":
                System.out.println("Nom du channel : ");
                String channelNameTest= sc.nextLine();
                System.out.println("User admin id : ");
                long channelAdminTest = Long.parseLong(sc.nextLine());

                Channel channelTest = new Channel(11, channelAdminTest,channelNameTest);

                System.out.println(channelDAO.create(channelTest));
                break;

            case "6":

                System.out.println("Id du user : ");
                long userChannelId = Long.parseLong(sc.nextLine());
                System.out.println("Id du channel: ");
                long channelId = Long.parseLong(sc.nextLine());

                UserChannel userChannelTest = new UserChannel(11, channelId, userChannelId);

                System.out.println(userChannelDAO.create(userChannelTest));
                break;

            case "7":
                UserChannelController ucdao = new UserChannelController();

                System.out.println("Id du channel : ");
                long channelIdList = Long.parseLong(sc.nextLine());

                Optional<List<UserChannel>> userChannels = ucdao.findAllUserFromAChannel(channelIdList);
                for(int i = 0 ; i < userChannels.stream().count() ; i++) {
                    System.out.println(userChannels.equals(i));
                }
                break;

            default:
                System.out.println("No such choice");
        }
    }
}
