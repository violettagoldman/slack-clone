package pijakogui.requestclient;

import com.bean.User;
import network.Client;
import network.Payload;
import pijakogui.Main;
import pijakogui.invoker.decorators.*;

public class UserClient {

    public static void findUser(long id) {
        Payload payload = PayloadBuilder.buildRequest("users/find", id);
        payload.setSenderID(Main.instanceID);
        Client.client.sendPayload(payload);
    }

    public static void signUpString(String username, String email, String pass, String secondPass){
        Payload payload = PayloadBuilder.buildRequest("users/signup", username, email, pass,secondPass);
        payload.setSenderID(Main.instanceID);
        Client.client.sendPayload(payload);
    }

}

