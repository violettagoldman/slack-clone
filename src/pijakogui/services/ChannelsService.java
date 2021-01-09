package pijakogui.services;

import com.bean.Channel;
import com.bean.Message;
import com.bean.User;
import com.bean.ResponseMessage;
import pijakogui.panel.ChannelPanel;
import pijakogui.panel.ChannelsPanel;
import pijakogui.panel.MyButton;
import pijakogui.panel.PijakoWindow;
import pijakogui.helpers.*;

import java.util.ArrayList;
import java.util.HashMap;


public class ChannelsService {
    private static final HashMap<String, ChannelPanel> channelsMap = new HashMap<>();

    //rajoute une chaine
    public static void addChannel(Channel channel){
        channelsMap.put(channel.getName(), ChannelsPanel.addChannels(channel));
    }
    //ajoute un message dans une chaine
    public static void addMessage(Message message, String title){
        ChannelPanel channel = channelsMap.get(title);
        if(channel == null)return;
        User user = UserHelper.findUserById(channel.getUsers(), message.getTransmitter_id());
        if(message.isSmiley())channel.smiley(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageId());
        else channel.messages(message.getMessage(), user.getUsername(), user.getIcone(), message.getMessageId());
        channel.getMessagesZone().validate();
    }

    public static void updateUsersConnected( String [] users, String title){
        channelsMap.get(title).updateListUser(users);
    }

    public static void updateNewChannel(ResponseMessage res){
        PijakoWindow.getNewChannel().updateNewChannel(res);
    }

    public static void updateChannels(){
        ArrayList<Channel> channels = UserService.getUser().getChannels();
        for(Channel channel : channels){
            addChannel(channel);
            for(Message message : channel.getMessages()){
                addMessage(message , channel.getName());
            }
        }
    }

    public static void channelAddUser(ResponseMessage<Object> response) {
    }
}
