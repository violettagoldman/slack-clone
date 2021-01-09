package pijakogui.requestclient;

import com.bean.Message;
import network.Client;
import network.Payload;

public class MessageClient {
    public static void sendMessage(Message message){
        Payload payload = PayloadBuilder.buildRequest("message/post",Payload.RequestType.MESSAGE_POST,message);
        Client.getInstance().sendPayload(payload);
    }
}
