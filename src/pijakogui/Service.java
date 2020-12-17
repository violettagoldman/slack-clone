package pijakogui;

import java.nio.channels.Channel;
import java.util.HashMap;
import pijakogui.*;


public class Service {
    private static final HashMap<String, ChannelPanel> channelsMap = new HashMap<>();

    public static void addChannel(String title, String user){
        channelsMap.put(title, ChannelsPanel.addChannels(title, user));
    }

    public static void addMessage(String message, String user, String title){
        channelsMap.get(title).addMessages(message, user);
        channelsMap.get(title).messagesZone.validate();
    }

    public static void addUser( String nickname, String title){
        MyButton button = MyButton.createBNameUser(nickname);
        channelsMap.get(title).addConnected(nickname);
        channelsMap.get(title).usersMap.put(nickname, button);
        channelsMap.get(title).listUser.add(button);
        channelsMap.get(title).validate();
    }

    public static void removeUser( String nickname, String title){
        ChannelPanel channel = channelsMap.get(title);
        channel.listUser.remove(channel.usersMap.get(nickname));
        channel.usersMap.remove(nickname);
        channelsMap.get(title).validate();
    }

}
