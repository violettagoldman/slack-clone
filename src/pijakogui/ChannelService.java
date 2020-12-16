package pijakogui;

import java.nio.channels.Channel;
import java.util.HashMap;
import pijakogui.*;


public class ChannelService {
    private static final HashMap<String, ChannelPanel> channelsMap = new HashMap<>();

    public static void addChannel(String title){
        channelsMap.put(title, ChannelsPanel.addChannels(title));
    }

    public static void addMessage(String message, String title){
        channelsMap.get(title).messagesZone.add(ChannelPanel.addMessages(message));
        channelsMap.get(title).messagesZone.validate();
    }

}
