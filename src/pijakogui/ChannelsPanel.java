package pijakogui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ChannelsPanel extends JPanel{
    private static JPanel listChannels;
    private static JPanel channels;
    private static CardLayout cardChannels;

    public ChannelsPanel(){
        this.setLayout( new BorderLayout() );
        listChannels = new JPanel();
        listChannels.setPreferredSize(new Dimension(200,0));
        listChannels.setBackground(MyColor.grayBlue());
        listChannels.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,new Color(101, 162, 201)));

        channels = new JPanel();
        cardChannels = new CardLayout();
        channels.setLayout(cardChannels);
        this.add( listChannels, BorderLayout.WEST );
        this.add( channels, BorderLayout.CENTER );
    }

    public static ChannelPanel addChannels(String title, String user){
        listChannels.add(MyButton.createBGoChannel(cardChannels, channels, title));
        ChannelPanel channel = new ChannelPanel(title, "id", user);
        channels.add(channel,title);
        cardChannels.show(channels, title);
        return channel;
    }

}
