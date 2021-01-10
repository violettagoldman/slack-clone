package pijakogui.requestclient;

import com.bean.User;
import network.Client;
import network.Payload;

public class UserClient {

    public static void findUser(long id) {
        Payload payload = PayloadBuilder.buildRequest("users/find", Payload.RequestType.USER_FIND, id);
        Client.getInstance().sendPayload(payload);
    }

    public static void signUp(String username, String email, String pass, String secondPass){
        Payload payload = PayloadBuilder.buildRequest("users/signup", Payload.RequestType.USER_SIGNUP, username, email, pass,secondPass);
        Client.getInstance().sendPayload(payload);
    }

    public static void signIn(String username, String pass){
        Payload payload = PayloadBuilder.buildRequest("users/signin", Payload.RequestType.USER_LOGIN,username, pass);
        Client.getInstance().sendPayload(payload);
    }
    public static void delete(long ID){
        Payload payload = PayloadBuilder.buildRequest("users/delete", Payload.RequestType.USER_DELETE, ID);
        Client.getInstance().sendPayload(payload);

    }
    public static void updateUser(User user){
        Payload payload = PayloadBuilder.buildRequest("users/update", Payload.RequestType.USER_EDIT, user);
        Client.getInstance().sendPayload(payload);
    }
    public static void updateIcone(long userID, String icone){
        Payload payload = PayloadBuilder.buildRequest("users/updateicone", Payload.RequestType.USER_EDIT_ICONE, userID,icone);
        Client.getInstance().sendPayload(payload);
    }
}

