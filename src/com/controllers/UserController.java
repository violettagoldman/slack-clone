package com.controllers;

import com.bean.User;
import com.dao.concret.UserDAO;
import com.dto.ResponseMessage;


public class UserController extends Controller {

    private final UserDAO userDAO = new UserDAO();

    public static ResponseMessage<User> login(User user){
        return null;
    }

    public static ResponseMessage<User> register(User user){
        return null;
    }


}