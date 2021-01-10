package pijakogui.panel;

import com.bean.ResponseMessage;
import com.bean.User;
import pijakogui.compoment.MyTextField;
import pijakogui.services.UserService;

import javax.swing.*;
import java.awt.*;

public class Profile extends MyPanel {
    private JTextField mail;
    private JTextField nickName;
    private JTextField password;
    private JTextField error;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Profile(CardLayout cardLayout, JPanel cardPanel){
        super();
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        ImageIcon image = new ImageIcon( this.getClass().getResource( "avatar/0.png"));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        this.panel.add(jlabel);

        this.panel.add(MyButton.createBChangeAvatar(cardLayout, cardPanel));

        error = MyTextField.BorderEmpty("");
        this.panel.add(error);

        mail = new MyTextField(UserService.getUser().getEmail());
        this.panel.add(mail);

        nickName = new MyTextField(UserService.getUser().getUsername());
        this.panel.add(nickName);

        password = new MyTextField("password");
        this.panel.add(password);

        this.panel.add(MyButton.createBSaveInformation(mail, nickName, password));

        this.panel.add(MyButton.createBDeleteAccount());
    }

    public void updateProfile(ResponseMessage res) {
        switch (res.getMessage()) {
            case USERNAME_NOT_VALID:
                error.setText("The username is not valid");
                break;
            case PASSWORD_NOT_VALID:
                error.setText("The password is not valid");
                break;
            case EMAIL_NOT_VALID:
                error.setText("This email already exist");
                break;
            case USERNAME_ALREADY_TAKEN:
                error.setText("This username already exist");
                break;
            case INFORMATION_USER_UPDATED:
                UserService.setUser((User) res.getData());
                PijakoWindow.seeChannels();
                break;
        }
    }

    public void deleteProfile(ResponseMessage res) {
        switch (res.getMessage()) {
            case USER_DELETED:
                System.exit(1);
                break;
        }
    }
}
