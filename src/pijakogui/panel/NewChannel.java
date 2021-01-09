package pijakogui.panel;

import com.bean.Channel;
import com.bean.ResponseMessage;
import com.bean.User;
import pijakogui.compoment.MyTextField;
import pijakogui.services.ChannelsService;
import pijakogui.services.UserService;

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

        error = MyTextField.BorderEmpty("");
        this.panel.add(error);

        channel = new MyTextField("Name of new channel");
        this.panel.add(channel);
        this.south.setPreferredSize(new Dimension(400,400));

        this.panel.add(MyButton.createBSaveChannel(cardLayout,cardPanel, channel, UserService.getUser().getId()));
    }

    public void updateNewChannel(ResponseMessage res) {
        switch (res.getMessage()) {
            case USERNAME_NOT_VALID:
                error.setText("The username is not valid");
                break;
            case PASSWORD_NOT_VALID:
                error.setText("The password is not valid");
                break;
            case USER_NOT_FOUND:
                error.setText("This username not exist");
                break;
            case INCORRECT_PASSWORD:
                error.setText("Password incorrect");
                break;
            case USER_CREATED:
                ChannelsService.addChannel((Channel)res.getData());
                cardLayout.show(cardPanel, "menu");
                break;
        }
    }
}
