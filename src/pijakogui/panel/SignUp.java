package pijakogui.panel;

import com.bean.ResponseMessage;
import com.bean.User;
import pijakogui.compoment.MyColor;
import pijakogui.compoment.MyTextField;
import pijakogui.services.ChannelsService;
import pijakogui.services.UserService;

import javax.swing.*;
import java.awt.*;

public class SignUp extends MyPanel{
    private JTextField error;
    private JTextField mail;
    private JTextField nickName;
    private JTextField password1;
    private JTextField password2;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public SignUp(CardLayout cardLayout, JPanel cardPanel) {
        super();
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        JTextField information = MyTextField.borderEmpty("Password : minimum 8 characters with at least 1 uppercase letter, 1 lowercase letter and 1 number");
        this.panel.add(information);

        error = MyTextField.borderEmpty("");
        this.panel.add(error);

        mail = new MyTextField("mail@gmail.com");
        this.panel.add(mail);

        nickName = new MyTextField("nickname");
        this.panel.add(nickName);

        password1 = MyTextField.password("Your password");
        this.panel.add(password1);

        password2 = MyTextField.password("Your password");
        this.panel.add(password2);

        this.panel.add(MyButton.createBNewProfile( error,mail, nickName, password1, password2, cardLayout, cardPanel));
        this.panel.add(MyButton.createBGoToConnect( cardLayout, cardPanel));

        this.allWhite();
    }

    private void allWhite(){
        this.mail.setForeground(MyColor.white());
        this.nickName.setForeground(MyColor.white());
        this.password1.setForeground(MyColor.white());
        this.password2.setForeground(MyColor.white());
    }

    public void updateSignUp(ResponseMessage res) {
        switch (res.getMessage()) {
            case EMAIL_NOT_VALID:
                error.setText("The mail is not valid");
                break;
            case USERNAME_NOT_VALID:
                error.setText("The username is not valid");
                break;
            case PASSWORD_NOT_VALID:
                error.setText("The password is not valid");
                break;
            case USERNAME_ALREADY_TAKEN:
                error.setText("This username already exist");
                break;
            case EMAIL_ALREADY_TAKEN:
                error.setText("This mail already exist");
                break;
            case ERROR_CREATION_USER:
                error.setText("an error occurred");
                break;
            case USER_CREATED:
                UserService.setUser((User) res.getData());
                PijakoWindow.updateEnvironment();
                ChannelsService.updateChannelsStart();
                cardLayout.show(cardPanel, "menu");
                break;
        }
    }
}
