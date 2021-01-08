package pijakogui.panel;

import pijakogui.compoment.MyTextField;

import javax.swing.*;
import java.awt.*;

public class Profile extends MyPanel {
    private JTextField mail;
    private JTextField nickName;
    private JTextField password;
    private JTextField error;
    public Profile(CardLayout cardLayout, JPanel cardPanel){
        super();
        ImageIcon image = new ImageIcon( this.getClass().getResource( "avatar/0.png"));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        this.panel.add(jlabel);

        this.panel.add(MyButton.createBChangeAvatar(cardLayout, cardPanel));

        error = MyTextField.BorderEmpty("");
        this.panel.add(error);

        mail = new MyTextField("mail@gmail.com");
        this.panel.add(mail);

        nickName = new MyTextField("nickname");
        this.panel.add(nickName);

        password = new MyTextField("password");
        this.panel.add(password);

        this.panel.add(MyButton.createBSaveInformation());

        this.panel.add(MyButton.createBDeleteAccount());
    }
}
