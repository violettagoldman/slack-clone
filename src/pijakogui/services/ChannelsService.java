package pijakogui.services;

import com.bean.Channel;
import com.bean.Message;
import com.bean.User;
import com.bean.ResponseMessage;
import pijakogui.panel.ChannelPanel;
import pijakogui.panel.ChannelsPanel;
import pijakogui.panel.PijakoWindow;
import pijakogui.helpers.*;

import java.util.ArrayList;
import java.util.HashMap;


public class ChannelsService {
    private static final HashMap<Long, ChannelPanel> channelsMap = new HashMap<>();

    //rajoute une chaine
    public static void addChannel(Channel channel){
        channelsMap.put(channel.getID(), ChannelsPanel.addChannels(channel));
    }
    //ajoute un message dans une chaine
    public static void addMessage(Message message, long id){
        ChannelPanel channel = channelsMap.get(id);
        if(channel == null)return;
        User user = UserHelper.findUserById(channel.getUsers(), message.getTransmitter_id());
        if(message.isSmiley())channel.smiley(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageId());
        else channel.messages(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageId());
        channel.getMessagesZone().validate();
    }

    public static void updateUsersConnected( String [] users, long id){
        channelsMap.get(id).updateListUserConnected(users);
    }

    public static void updateNewChannel(ResponseMessage res){
        PijakoWindow.getNewChannel().updateNewChannel(res);
    }

    public static void updateChannelsStart(){
        ArrayList<Channel> channels = UserService.getUser().getChannels();
        for(Channel channel : channels){
            addChannel(channel);
            for(Message message : channel.getMessages()){
                addMessage(message , channel.getID());
            }
        }
    }

    public static void channelAddUser(ResponseMessage<Object> response) {
        Channel channel = (Channel)response.getData();
        long id = channel.getID();
        ChannelPanel channelpanel = channelsMap.get(id);
        if(UserHelper.findUserById(channelpanel.getUsers(), UserService.getUser().getId()) == null)return;
        if(channelpanel == null)addChannel((Channel)response.getData());
        else {
            channelpanel.updateUsers(channel.getUsers());
            channelpanel.clearError();
        }
    }

    public static void addUserError(ResponseMessage response){
        channelsMap.get(response.getData()).updateAddUser(response);
    }
}
