package pijakogui.services;

import com.bean.ResponseMessage;
import com.bean.User;
import pijakogui.PijakoWindow;

public class UserService {
    private static User session;

    public static void signIn(ResponseMessage res){
        PijakoWindow.getSignUp().updateSignUp(res);
    }

}
