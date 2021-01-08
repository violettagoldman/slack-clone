package pijakogui.helpers;

import com.bean.User;

import java.util.List;

public class UserHelper {
    public static User findUserById(List<User> users, long id){
        User res=null;
        for(User user : users){
            if(user.getId()==id){
                res=user;
                break;
            }
        }
        return res;
    }
}
