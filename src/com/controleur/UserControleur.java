package com.controleur;

import com.bean.ResponseMessage;
import com.bean.User;
import com.dao.concret.UserDAO;
import com.helpers.PasswordHelper;

import java.security.NoSuchAlgorithmException;


public class UserControleur {

    private final UserDAO userDAO = new UserDAO();

    public ResponseMessage<User> signIn(String username, String pass) {
        try {
            User user = userDAO.findWithUsername(username).getData(); //com.controleur

            if (!PasswordHelper.comparePassAndHashedPassword(pass, user.getPassword())) {
                user = new User();
            }

            return new ResponseMessage<User>(user, ResponseMessage.messages.USER_SIGN_IN, 200);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_HASHING, 500);
        }
    }
}
