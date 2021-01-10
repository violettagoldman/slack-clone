package pijakogui.services;

import com.bean.ResponseMessage;
import com.bean.User;

import pijakogui.panel.PijakoWindow;

import java.sql.Timestamp;

public class UserService {
    private static User session;

    public static User getUser() {
        return session;
    }

    public static void setUser(User user) {
        session = user;
    }

    public static void updateUser(ResponseMessage responseMessage){
        (PijakoWindow.getProfile()).updateProfile(responseMessage);
    }
    public static void signUp(ResponseMessage res){
        PijakoWindow.getSignUp().updateSignUp(res);
    }

    public static void login(ResponseMessage res){
        PijakoWindow.getLogin().updateLogin(res);
    }

    public static void logout(ResponseMessage res){
        UserService.setUser(null);
        PijakoWindow.rebuilEnvironment();
    }

}
