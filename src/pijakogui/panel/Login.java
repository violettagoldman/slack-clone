package pijakogui.panel;

import com.bean.ResponseMessage;
import com.bean.User;
import pijakogui.compoment.MyTextField;
import pijakogui.services.ChannelsService;
import pijakogui.services.UserService;

import javax.swing.*;
import java.awt.*;

public class Login extends MyPanel{
    private JTextField nickname;
    private JTextField password;
    private JTextField error;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Login(CardLayout cardLayout, JPanel cardPanel){
        super();
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        error = MyTextField.borderEmpty("");
        this.panel.add(error);

        nickname = new MyTextField("Your nickname");
        this.panel.add(nickname);

        password = MyTextField.password("Your password");
        this.panel.add(password);


        this.south.setPreferredSize(new Dimension(400,400));

        this.panel.add(MyButton.createBLogin(nickname, password));
        this.panel.add(MyButton.createBSendMail(this));
        this.panel.add(MyButton.createBSignUp(cardLayout, cardPanel));
    }

    public void updateLogin(ResponseMessage res) {
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
            case USER_IDENTIFIED:
                UserService.setUser((User) res.getData());
                PijakoWindow.updateEnvironment();
                ChannelsService.updateChannelsStart();
                network.Client.getInstance().setUser(UserService.getUser().getUsername());
                cardLayout.show(cardPanel, "menu");
                break;
        }
    }


}
