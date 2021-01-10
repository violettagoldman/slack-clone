package pijakogui;

import com.bean.Channel;
import network.Payload;
import pijakogui.panel.PijakoWindow;
import pijakogui.services.ChannelsService;
import pijakogui.services.UserService;

public class ServiceRoute {

    public static void invokeService(Payload payload){
        switch (payload.getRequestType()){
            case USER_LOGIN:
                UserService.login(payload.getResponse());
                break;
            case USER_EDIT:
                UserService.updateUser(payload.getResponse());
                break;
            case USER_EDIT_ICONE:
                UserService.updateUser(payload.getResponse());
                break;
            case USER_FIND:
                break;
            case USER_DELETE:
                if(payload.getResponse().getStatus()<400){
                    UserService.setUser(null);
                    //network.Client.getInstance().setUser(null);
                    PijakoWindow.rebuilEnvironment();
                }
                break;
            case USER_SIGNUP:
                UserService.signUp(payload.getResponse());
                break;
            case CHANNEL_EDIT:
                break;
            case CHANNEL_CREATE:
                ChannelsService.updateNewChannel(payload.getResponse());
                break;
            case CHANNEL_ADD_USER:
                if(payload.getResponse().getStatus()>=400){
                    ChannelsService.addUserError(payload.getResponse());
                }else{
                    ChannelsService.channelAddUser(payload.getResponse());
                }
                break;
            case CHANNEL_REMOVE_USER:
                ChannelsService.channelRemoveUser(payload.getResponse());
                break;
            case CHANNEL_DELETE:
                ChannelsService.removeChannel(payload.getResponse());
                break;
            case CHANNEL_FINDALL:
                break;
            case CHANNEL_MESSAGES:
                break;
            case MESSAGE_POST:
                if(payload.getResponse().getStatus()<400){
                    ChannelsService.addMessage(payload.getResponse());
                }
                break;
            case MESSAGE_DELETE:
                if(payload.getResponse().getStatus()<400){
                    ChannelsService.deleteMessage(payload.getResponse());
                }
            default:
        }
    }
}
