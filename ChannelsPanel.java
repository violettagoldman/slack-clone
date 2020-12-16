package PijakoGUI;

import javax.swing.*;
import java.awt.*;

public class ChannelsPanel extends JPanel{
    public static JPanel listChannels;
    public static JPanel channels;
    public static CardLayout cardChannels;

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

    public static void addChannels(String title){
        listChannels.add(MyButton.createBGoChannel(cardChannels, channels, title));
        channels.add(new ChannelPanel(title, "id"),title);
        cardChannels.show(channels, title);
    }

}
