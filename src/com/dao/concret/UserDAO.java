package com.dao.concret;

import com.bean.ResponseMessage;
import com.bean.User;
import com.dao.DAO;
import com.helpers.PasswordHelper;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User> {

    public ResponseMessage<User> find(long id) {
        User user = new User();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM user WHERE id = " + id
            );

            if(result.first()) {
                user = new User(
                        id,
                        result.getString("username"),
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

    public ResponseMessage<ArrayList<User>> findAll () {

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

    public ResponseMessage<User> create(User userObj) {

        if (this.isUsernameValid(userObj.getUsername()) && this.isEmailValid(userObj.getEmail()) && this.isPasswordValid(userObj.getPassword())) {

            try {
                PreparedStatement prepare = this.connect.prepareStatement(
                        "INSERT INTO user (username,email,hashed_password,created_at) VALUES(?,?,?,?)"
                );

                prepare.setString(1,userObj.getUsername());
                prepare.setString(2,userObj.getEmail());
                prepare.setString(3, PasswordHelper.hashPassword(userObj.getPassword()));
                prepare.setTimestamp(4,userObj.getCreatedAt());

                prepare.executeUpdate();
                userObj = this.findWithEmail(userObj.getEmail()).getData();

                return new ResponseMessage<User>(userObj, ResponseMessage.messages.USER_CREATE, 200);

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e);
                return new ResponseMessage<User>(null, ResponseMessage.messages.USER_ALREADY_EXISTS, 409);
            } catch (SQLException e) {
                System.out.println(e);
                return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_BDD, 500);
            } catch (NoSuchAlgorithmException e){
                System.out.println(e);
                return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_HASHING, 500);
            }
        } else {
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_INFO_USER,000);
        }
    }

    public ResponseMessage<User> update(User userObj) {
        try {

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "UPDATE user SET username = '" + userObj.getUsername() + "', "
                    + "email = '" + userObj.getEmail() + "', "
                    + "hashed_password = '" + userObj.getPassword() + "' "
                    + "WHERE id = " + userObj.getId()
            );

            userObj = this.find(userObj.getId()).getData();

            return new ResponseMessage<User>(userObj, ResponseMessage.messages.USER_UPDATE, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public ResponseMessage<User> delete(long id) {
        try {

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM user WHERE id = " + id
            );

            return new ResponseMessage<User>(null, ResponseMessage.messages.USER_DELETE, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public ResponseMessage<User> signIn(String username, String pass) {
        try {
            User user = findWithUsername(username).getData();

            if (!PasswordHelper.comparePassAndHashedPassword(pass, user.getPassword())) {
                user = new User();
            }

            return new ResponseMessage<User>(user, ResponseMessage.messages.USER_SIGN_IN, 200);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_HASHING, 500);
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
