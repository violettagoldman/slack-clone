package pijakogui.services;

import com.bean.Channel;
import com.bean.ResponseMessage;
import pijakogui.panel.ChannelPanel;
import pijakogui.panel.ChannelsPanel;
import pijakogui.panel.MyButton;
import pijakogui.panel.PijakoWindow;

import java.util.ArrayList;
import java.util.HashMap;


public class ChannelsService {
    private static final HashMap<String, ChannelPanel> channelsMap = new HashMap<>();

    public static void addChannel(String title, long adminUserId){
        Object channel;
        channelsMap.put(title, ChannelsPanel.addChannels(title, adminUserId));
    }

    public static void addMessage(String message, String user, String avatar, String title){
        channelsMap.get(title).messages(message, user, avatar);
        channelsMap.get(title).getMessagesZone().validate();
    }

    public static void addSmiley(String smiley, String user, String avatar, String title){
        channelsMap.get(title).smiley(smiley, user, avatar);
    }

    public static void addUser( String nickname, String title){
        MyButton button = MyButton.createBNameUser(nickname);
        channelsMap.get(title).connected(nickname);
        channelsMap.get(title).getUsersMap().put(nickname, button);
        channelsMap.get(title).getListUser().add(button);
        channelsMap.get(title).validate();
    }

    public static void removeUser( String nickname, String title){
        ChannelPanel channel = channelsMap.get(title);
        channel.getListUser().remove(channel.getUsersMap().get(nickname));
        channel.getUsersMap().remove(nickname);
        channelsMap.get(title).validate();
    }

    public static void updateUsersConnected( String [] users, String title){
        channelsMap.get(title).updateListUser(users);
    }

    public static void updateNewChannel(ResponseMessage res){
        PijakoWindow.getNewChannel().updateNewChannel(res);
    }

    public static void updateChannel(){
        ArrayList<Channel> channels = UserService.getUser().getChannels();
        for(Channel channel : channels){
            addChannel(channel.getName(), channel.getAdminUserId());
        }
    }

}
