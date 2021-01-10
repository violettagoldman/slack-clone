package pijakogui.panel;

import com.bean.Channel;
import com.bean.ResponseMessage;
import pijakogui.compoment.MyTextField;
import pijakogui.services.ChannelsService;

import javax.swing.*;
import java.awt.*;

public class NewChannel extends MyPanel {
    private JTextField channel;
    private JTextField error;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public NewChannel(CardLayout cardLayout, JPanel cardPanel) {
        super();

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        this.add(MyTextField.borderEmpty("the name of the channel must not contain spaces and two characters"));

        error = MyTextField.borderEmpty("");
        this.panel.add(error);

        channel = new MyTextField("Name of new channel");
        this.panel.add(channel);
        this.south.setPreferredSize(new Dimension(400,400));

        this.panel.add(MyButton.createBSaveChannel(channel));
    }

    public void updateNewChannel(ResponseMessage res) {
        switch (res.getMessage()) {
            case ERROR_CREATION_CHANNEL:
                error.setText("an error has occurred, try again");
                break;
            case CHANNEL_CREATED:
                ChannelsService.addChannel((Channel)res.getData());
                error.setText("");
                cardLayout.show(cardPanel, "menu");
                PijakoWindow.seeChannels();
                break;
        }
    }
}
