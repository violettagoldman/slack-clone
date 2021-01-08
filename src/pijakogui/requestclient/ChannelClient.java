package pijakogui.requestclient;

import com.bean.Channel;
import com.invoker.decorators.MethodRoute;
import network.Client;
import network.Payload;

import java.util.List;

public class ChannelClient {
    public static void getChannelsByUserID(long ID){
        Payload payload=PayloadBuilder.buildRequest("channels/getchannelsbyuserid", Payload.RequestType.CHANNEL_FINDALL,ID);
        Client.getInstance().sendPayload(payload);
    }
    public static void createChannel(long ID){
        Payload payload=PayloadBuilder.buildRequest("channels/delete", Payload.RequestType.CHANNEL_CREATE,ID);
        Client.getInstance().sendPayload(payload);

    }
    public static void editChannel(Channel actualChannel, String name, long adminId){
        Payload payload=PayloadBuilder.buildRequest("channels/updateinformation", Payload.RequestType.CHANNEL_EDIT,actualChannel,name,adminId);
        Client.getInstance().sendPayload(payload);

    }

    public static void deleteChannel(long ID){
        Payload payload=PayloadBuilder.buildRequest("channels/getchannelsbyuserid", Payload.RequestType.CHANNEL_DELETE,ID);
        Client.getInstance().sendPayload(payload);

    }
}
