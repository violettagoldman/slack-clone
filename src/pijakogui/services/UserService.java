package pijakogui.services;

import com.bean.ResponseMessage;
import com.bean.User;
import pijakogui.PijakoWindow;
import pijakogui.invoker.decorators.MainRoute;
import pijakogui.invoker.decorators.MethodRoute;

public class UserService {
    private static User session;
    public static void signUp(ResponseMessage res){
        PijakoWindow.getSignUp().updateSignUp(res);
    }
}
