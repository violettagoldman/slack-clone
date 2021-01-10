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
    private BaseChannel baseChannel;


    public ChannelsPanel(){
        this.setLayout( new BorderLayout() );
        listChannels = new JPanel();
        listChannels.setPreferredSize(new Dimension(200,0));
        listChannels.setBackground(MyColor.grayBlue());
        listChannels.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,new Color(101, 162, 201)));

        channels = new JPanel();
        cardChannels = new CardLayout();
        channels.setLayout(cardChannels);
        channels.add(baseChannel, "base");
        cardChannels.show(channels,"base");
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

    public void removeChannelButton(long channelID){
        channelButtonMap.get(channelID).setVisible(false);
        channelButtonMap.get(channelID).validate();
        listChannels.remove(channelButtonMap.get(channelID));
        listChannels.validate();
        this.validate();
        channelButtonMap.remove(channelID);
    }

    public void removeChannelPanel(ChannelPanel channelPanel){
        showBase();
        cardChannels.removeLayoutComponent(channelPanel);
        this.remove(channelPanel);
        this.validate();
    }


    public void updateChannels(ResponseMessage res){

    }

    public void showBase(){
        cardChannels.show(channels, "base");
        channels.validate();
        listChannels.validate();
        this.validate();
    }

}
