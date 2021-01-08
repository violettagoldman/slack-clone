package pijakogui.services;

import com.bean.ResponseMessage;
import com.bean.User;

import pijakogui.panel.PijakoWindow;

import java.sql.Timestamp;

public class UserService {
    private static User session;

    public static void test(){
        session = new User(02155, "Jeanne", "jeannne@gmail.com", "12345Test!", new Timestamp(System.currentTimeMillis()), "avatar/4.png"  );
        System.out.println(session.toString());
    }

    public static User getUser() {
        return session;
    }

    public static void setUser(User user) {
        session = user;
    }

    public static void signUp(ResponseMessage res){
        PijakoWindow.getSignUp().updateSignUp(res);
    }

    public static void login(ResponseMessage res){
        PijakoWindow.getLogin().updateLogin(res);
    }

}
