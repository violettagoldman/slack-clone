package pijakogui.panel;

import com.bean.ResponseMessage;
import com.bean.Channel;
import com.bean.User;
import pijakogui.compoment.MyColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ChannelsPanel extends JPanel{
    private JPanel listChannels;
    private JPanel channels;
    private CardLayout cardChannels;
    private HashMap<Long, MyButton> channelButtonMap = new HashMap<>();


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

    public ChannelPanel addChannels(Channel channel){
        MyButton button = MyButton.createBGoChannel(cardChannels, channels, channel.getName());
        channelButtonMap.put(channel.getID(), button);
        listChannels.add(button );
        ChannelPanel channelPanel = new ChannelPanel(channel.getName(), channel.getID(), channel.getAdminUserId(), (ArrayList< User >) channel.getUsers());
        channels.add(channelPanel,channel.getName());
        cardChannels.show(channels, channel.getName());
        return channelPanel;
    }

    public void notifyNewMessage(long id){
        MyButton button = channelButtonMap.get(id);
        button.setForeground(MyColor.blueUser());
        listChannels.validate();
        this.validate();
    }

    public void removeChannelButton(Channel channel){
        listChannels.remove(channelButtonMap.get(channel.getID()));
        listChannels.validate();
        this.validate();
        channelButtonMap.remove(channel.getID());
    }

    public void removeChannelPanel(ChannelPanel channelPanel){
        cardChannels.removeLayoutComponent(channelPanel);
    }

    public void updateChannels(ResponseMessage res){

    }

}
