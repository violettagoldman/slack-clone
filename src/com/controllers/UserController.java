package com.controllers;

import com.helpers.PasswordHelper;
import com.models.ResponseMessage;
import com.models.User;
import com.srf.decorators.ControllerRoute;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


//Get : /user/3
@ControllerRoute(route="/user")
public class UserController extends com.controllers.Controller {

    void getUsr(int id){}
    private UserController() {
        super();
    }
    /*
     * Creer un utilisateur dans la BDD
     * */


    public static ResponseMessage<User> createUser(User user)  {
        try {
            String createUserString = "INSERT INTO user (username,email,hashed_password,created_at) VALUES (?,?,?,?)";
            PreparedStatement createUser = connexion.prepareStatement(createUserString);
            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getEmail());
            createUser.setString(3, PasswordHelper.hashPassword(user.getPassword()));
            createUser.setTimestamp(4, java.sql.Timestamp.valueOf(user.getCreatedAt()));
            createUser.executeUpdate();
            return new ResponseMessage<User>(user, "User crée avec succès", 200);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, "L'utilisateur existe déjà", 409);
        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<User>(null, "Err BDD", 500);
        } catch (NoSuchAlgorithmException e){
            System.out.println(e);
            return new ResponseMessage<User>(null, "Err hashing", 500);
        }
    }
}
