package com.controllers;

import com.bean.*;
import com.dao.impl.ChannelDAO;
import com.dao.impl.MessageDAO;
import com.dao.impl.UserDAO;
import com.invoker.decorators.ControllerRoute;
import com.invoker.decorators.MethodRoute;
import org.apache.commons.cli.Option;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.helpers.PasswordHelper.comparePassAndHashedPassword;
import static com.helpers.PasswordHelper.hashPassword;
import static com.helpers.RegexHelper.*;
import static com.bean.ResponseMessage.Messages.*;
@ControllerRoute("users")
public class UserController extends Controller {

    private final static UserDAO userDAO = new UserDAO();
    private final static ChannelDAO channelDAO = new ChannelDAO();
    private final static MessageDAO messageDAO = new MessageDAO();
    @ControllerRoute("find")
    public static ResponseMessage find(long id) throws SQLException {

        Optional userOp = userDAO.find(id);

        if (userOp.isEmpty()) {
            return new ResponseMessage(null, USER_NOT_FOUND, 400);
        }
        User user =(User) userOp.get();
        ArrayList<Channel> userChannels =(ArrayList<Channel>) channelDAO.findChannelsbyUserId(user.getId()).get();
        for(Channel channel : userChannels){
            channel.setMessages(
                    (ArrayList< Message >)messageDAO.findChannelMessages(channel.getID()).get()
            );
        }
        user.setChannels(userChannels);
        return new ResponseMessage(user, USER_FOUND, 200);

    }

    @ControllerRoute("findAll")
    public static ResponseMessage findAll() throws SQLException {

        List<com.bean.User> usersOp = userDAO.findAll().get();

        if (usersOp.size() < 1) {
            return new ResponseMessage(null, NO_USER_IN_DB, 400);
        }

        return new ResponseMessage(usersOp, ALL_USERS_FOUND, 200);

    }

    @ControllerRoute("findWithEmail")
    public static ResponseMessage findWithEmail(String email) throws SQLException {

        Optional userOp = userDAO.findWithEmail(email);

        if (userOp.isEmpty()) {
            return new ResponseMessage(null, USER_NOT_FOUND, 400);
        }

        return new ResponseMessage(userOp.get(), USER_FOUND, 200);

    }
    @ControllerRoute("findWithUsername")
    public static ResponseMessage findWithUsername(String username) throws SQLException {

        Optional userOp = userDAO.findWithUsername(username);

        if (userOp.isEmpty()) {
            return new ResponseMessage(null, USER_NOT_FOUND, 400);
        }

        return new ResponseMessage(userOp.get(), USER_FOUND, 200);

    }

    @ControllerRoute("delete")
    public static ResponseMessage delete(long id) throws SQLException {

        userDAO.delete(id);

        return new ResponseMessage(null, USER_DELETED, 200);

    }

    /**
     * Find user with username and compare password
     * @param username
     * @param pass
     * @return Data, message and status
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */

    public static  ResponseMessage signIn(String username, String pass) throws SQLException, NoSuchAlgorithmException {

            // We check if the information is valid
        if (!isUsernameValid(username)) {
            return new ResponseMessage(null, USERNAME_NOT_VALID, 400);
        } else if (!isPasswordValid(pass)) {
            return new ResponseMessage(null, PASSWORD_NOT_VALID, 400);
        }

        Optional userOp = userDAO.findWithUsername(username);

            // We check if the user exists
        if (userOp.isEmpty()) {
            return new ResponseMessage(null, USER_NOT_FOUND, 400);
        }

        User user = (User) userOp.get();

        ArrayList<Channel> userChannels =(ArrayList<Channel>) channelDAO.findChannelsbyUserId(user.getId()).get();
        for(Channel channel : userChannels){
            channel.setMessages(
                    (ArrayList< Message >)messageDAO.findChannelMessages(channel.getID()).get()
            );
        }
        user.setChannels(userChannels);
            // We check if the password matches the hashed password
        if (!comparePassAndHashedPassword(pass, user.getPassword())) {
            return new ResponseMessage(null, INCORRECT_PASSWORD, 400);
        }

            // The information is correct - the user is identified
        return new ResponseMessage(user, USER_IDENTIFIED, 200);

    }

    // EN STATIC PARCE QU'APPELER DANS MYBUTTON.JAVA. A CHANGER SI BESOIN.
    /**
     * Create user and add to DB
     * @param username
     * @param email
     * @param pass
     * @param secondPass
     * @return Data, message, status
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @MethodRoute("signup")
    public static ResponseMessage signUp(String username, String email, String pass, String secondPass) throws SQLException, NoSuchAlgorithmException {


            // We check if the information is valid
        if (!isUsernameValid(username)) {
            return new ResponseMessage(null, USERNAME_NOT_VALID, 400);
        } else if (!isPasswordValid(pass)) {
            return new ResponseMessage(null, PASSWORD_NOT_VALID, 400);
        } else if (!isEmailValid(email)) {
            return new ResponseMessage(null, EMAIL_NOT_VALID, 400);
        }

        Optional userOp;

            // We check if the username is already taken by another user
        userOp = userDAO.findWithUsername(username);
        if (userOp.isPresent()) {
            return new ResponseMessage(null, USERNAME_ALREADY_TAKEN, 400);
        }

            // We check if the email is already taken by another user
        userOp = userDAO.findWithEmail(email);
        if (userOp.isPresent()) {
            return new ResponseMessage(null, EMAIL_ALREADY_TAKEN, 400);
        }

            // We check if the two passes are identical
        if (!pass.equals(secondPass)) {
            return new ResponseMessage(null, PASSES_NOT_IDENTICAL, 400);
        }

            // We create the user and add them to the DB
        User user = new User(1,username,email,pass);
        Optional createdUser = userDAO.create(user);

        if (createdUser.isEmpty()) {
            return new ResponseMessage(null, ERROR_CREATION_USER, 400);
        }

        return new ResponseMessage(createdUser.get(), USER_CREATED, 200);

    }

    /**
     * Update user information in DB
     * @param actualUser
     * @param username
     * @param email
     * @param pass
     * @return Data, message, status
     * @throws SQLException
     */

    @ControllerRoute("update")
    public ResponseMessage update(User actualUser, String username, String email, String pass, String icone) throws SQLException, NoSuchAlgorithmException {

            // We check if the information is valid
        if (!isUsernameValid(username)) {
            return new ResponseMessage(null, USERNAME_NOT_VALID, 400);
        } else if (!isPasswordValid(pass)) {
            return new ResponseMessage(null, PASSWORD_NOT_VALID, 400);
        } else if (!isEmailValid(email)) {
            return new ResponseMessage(null, EMAIL_NOT_VALID, 400);
        }

        Optional userOp;

            // We check if the username is already taken by another user
        userOp = userDAO.findWithUsername(username);
        if (userOp.isPresent()) {
            User userOpGet = (User) userOp.get();
            if (userOpGet.getId() != actualUser.getId()) {
                return new ResponseMessage(null, USERNAME_ALREADY_TAKEN, 400);
            }
        }

            // We check if the email is already taken by another user
        userOp = userDAO.findWithEmail(email);
        if (userOp.isPresent()) {
            User userOpGet = (User) userOp.get();
            if (userOpGet.getId() != actualUser.getId()) {
                return new ResponseMessage(null, EMAIL_ALREADY_TAKEN, 400);
            }
        }

            // We check if values to update are null
        if (username == null) {
            username = actualUser.getUsername();
        }
        if (email == null) {
            email = actualUser.getEmail();
        }
        if (pass == null) {
            pass = actualUser.getPassword();
        } else {
            pass = hashPassword(pass);
        }

            // We update the user in DB
        actualUser.setUsername(username);
        actualUser.setEmail(email);
        actualUser.setPassword(pass);
        Optional updatedUser = userDAO.update(actualUser);

        return new ResponseMessage(updatedUser.get(), INFORMATION_USER_UPDATED, 200);

    }

    /**
     * Update user icone in DB
     * @param actualUser
     * @param icone
     * @return Data, message, status
     * @throws SQLException
     */
    public ResponseMessage updateIcone(User actualUser, String icone) throws SQLException {

        actualUser.setIcone(icone);
        Optional updatedUser = userDAO.updateIcone(actualUser);

        return new ResponseMessage(updatedUser.get(), ICONE_USER_UPDATED, 200);
    }
}