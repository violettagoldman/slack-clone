package pijakogui.services;

import network.Payload;

public class ServiceRoute {

    public static void invokeService(Payload payload){
        switch (payload.getRequestType()){
            case USER_LOGIN:
                UserService.login(payload.getResponse());
            case USER_EDIT:
            case USER_FIND:
            case USER_DELETE:
            case USER_SIGNUP:
                UserService.signUp(payload.getResponse());
            case CHANNEL_EDIT:
            case CHANNEL_CREATE:
                ChannelsService.updateNewChannel(payload.getResponse());
            case CHANNEL_ADD_USER_ERROR :
                ChannelsService.addUserError(payload.getResponse());
            case CHANNEL_ADD_USER:
                ChannelsService.channelAddUser(payload.getResponse());
            case CHANNEL_DELETE:
            case CHANNEL_FINDALL:
            case CHANNEL_MESSAGES:
            case MESSAGE_POST:
            default:
        }
    }
}
