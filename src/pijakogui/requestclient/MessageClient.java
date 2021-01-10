package pijakogui.requestclient;

import com.bean.Message;
import network.Client;
import network.Payload;

public class MessageClient {
    private final static String uri = "message/";
    public static void sendMessage(Message message){
        Payload payload = PayloadBuilder.buildRequest(uri + "post",Payload.RequestType.MESSAGE_POST,message);
        Client.getInstance().sendPayload(payload);
    }
    public static void deleteMessage(long messageID){
        Payload payload = PayloadBuilder.buildRequest(uri + "delete",Payload.RequestType.MESSAGE_DELETE,messageID);
        Client.getInstance().sendPayload(payload);
    }

}
