package pijakogui.services;

import network.Payload;

public class ServiceRoute {

    public static void invokeService(Payload payload){
        switch (payload.getRequestType()){
            case USER_LOGIN:
                UserService.login(payload.getResponse());
                break;
            case USER_EDIT:
                break;
            case USER_FIND:
                break;
            case USER_DELETE:
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
            case CHANNEL_DELETE:
                break;
            case CHANNEL_FINDALL:
                break;
            case CHANNEL_MESSAGES:
                break;
            case MESSAGE_POST:
                break;
            default:
        }
    }
}
