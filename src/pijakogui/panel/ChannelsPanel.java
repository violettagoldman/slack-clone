package pijakogui.panel;

import com.bean.ResponseMessage;
import com.bean.Channel;
import pijakogui.compoment.MyColor;

import javax.swing.*;
import java.awt.*;

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

    public static ChannelPanel addChannels(Channel channel){
        listChannels.add(MyButton.createBGoChannel(cardChannels, channels, channel.getName()));
        ChannelPanel channelPanel = new ChannelPanel(channel.getName(), channel.getID(), channel.getAdminUserId(), channel.getUsers());
        channels.add(channelPanel,channel.getName());
        cardChannels.show(channels, channel.getName());
        return channelPanel;
    }

    public void updateChannels(ResponseMessage res){

    }

}
