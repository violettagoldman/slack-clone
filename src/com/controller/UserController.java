package com.controller;

import com.bean.ResponseMessage;
import com.bean.User;
import com.dao.concret.UserDAO;
import com.helpers.PasswordHelper;
import com.jdbc.ConnectionSQL;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserController {

    private final UserDAO userDAO = new UserDAO();
    public Connection connect = ConnectionSQL.getInstance();

    public ResponseMessage<User> signIn(String username, String pass) {
        try {
            User user = findWithUsername(username).getData(); //com.controleur

            if (!PasswordHelper.comparePassAndHashedPassword(pass, user.getPassword())) {
                user = new User();
            }

            return new ResponseMessage<User>(user, ResponseMessage.messages.USER_SIGN_IN, 200);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_HASHING, 500);
        }
    }

    public ResponseMessage<ArrayList<User>> findAll() {

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM user"
            );

            List<User> users = new ArrayList<>();

            while(result.next()) {
                User user = new User(
                        result.getLong("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("hashed_password"),
                        result.getTimestamp("created_at")
                );
                users.add(user);
            }

            return new ResponseMessage(users,ResponseMessage.messages.ALL_USERS_FIND,200);

        }catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage(null, ResponseMessage.messages.ERR_BDD, 500);
        }

    }

    public ResponseMessage<User> findWithEmail(String email) {
        User user = new User();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM user WHERE email = '" + email + "'"
            );

            if(result.first()) {
                user = new User(
                        result.getLong("id"),
                        result.getString("username"),
                        email,
                        result.getString("hashed_password"),
                        result.getTimestamp("created_at")
                );
            }

            return new ResponseMessage<User>(user, ResponseMessage.messages.USER_FIND, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public ResponseMessage<User> findWithUsername(String username) {
        User user = new User();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM user WHERE username = '" + username + "'"
            );

            if(result.first()) {
                user = new User(
                        result.getLong("id"),
                        username,
                        result.getString("email"),
                        result.getString("hashed_password"),
                        result.getTimestamp("created_at")
                );
            }

            return new ResponseMessage<User>(user, ResponseMessage.messages.USER_FIND, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean isUsernameValid(String username) {
        String regex = "^[\\w-]{2,}$";
        return username.matches(regex);
    }

    public static boolean isPasswordValid(String pass) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        return pass.matches(regex);
    }
}
