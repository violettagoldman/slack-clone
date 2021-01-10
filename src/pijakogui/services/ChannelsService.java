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
        channelsMap.put(channel.getID(), PijakoWindow.getChannelsPanel().addChannels(channel));
    }
    //ajoute un message dans une chaine
    public static void addMessage(ResponseMessage responseMessage){
        Message message=(Message)responseMessage.getData();
        Long id = message.getChannelID();
        ChannelPanel channel = channelsMap.get(id);
        if(channel == null)return;
        User user = UserHelper.findUserById(channel.getUsers(), message.getTransmitterID());
        if(message.isSmiley())channel.smiley(
                message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageID(), message.getCreatedAt());
        else channel.messages(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageID(), message.getCreatedAt());
        channel.getMessagesZone().validate();
    }

    public static void addMessage(Message message, Long id){
        ChannelPanel channel = channelsMap.get(id);
        if(channel == null)return;
        User user = UserHelper.findUserById(channel.getUsers(), message.getTransmitterID());
        if(message.isSmiley())channel.smiley(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageID(),  message.getCreatedAt());
        else channel.messages(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageID(),  message.getCreatedAt());
        PijakoWindow.getChannelsPanel().notifyNewMessage(id);
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
        if(channel==null)return;
        long id = channel.getID();
        ChannelPanel channelpanel = channelsMap.get(id);
        if(UserHelper.findUserById(channel.getUsers(), UserService.getUser().getId()) == null)return;
        if(channelpanel == null)addChannel((Channel)response.getData());
        else {
            channelpanel.updateUsers((ArrayList<User>)channel.getUsers());
            channelpanel.clearError();
        }
    }

    public static void channelRemoveUser(ResponseMessage<Object> response) {
        Channel channel = (Channel)response.getData();
        if(channel==null)return;
        long id = channel.getID();
        ChannelPanel channelpanel = channelsMap.get(id);
        if(channelpanel == null)return;
        if(UserHelper.findUserById(channel.getUsers(), UserService.getUser().getId()) == null){
            PijakoWindow.getChannelsPanel().removeChannelButton(channel);
            PijakoWindow.getChannelsPanel().removeChannelPanel(channelpanel);
        }
        else {
            channelpanel.updateUsers((ArrayList<User>)channel.getUsers());
        }
    }

    public static void removeChannel(ResponseMessage<Object> response) {
        Channel channel = (Channel)response.getData();
        if(channel==null)return;
        long id = channel.getID();
        ChannelPanel channelpanel = channelsMap.get(id);
        if(channelpanel == null)return;
        PijakoWindow.getChannelsPanel().removeChannelButton(channel);
        PijakoWindow.getChannelsPanel().removeChannelPanel(channelpanel);
    }

    public static void addUserError(ResponseMessage response){
        channelsMap.get(response.getData()).updateAddUser(response);
    }
}
