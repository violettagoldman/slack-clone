package pijakogui;

import javax.swing.*;
import java.awt.*;

public class Login extends MyPanel{
    private JTextField nickname;
    private JTextField password;
    public Login(CardLayout cardLayout, JPanel cardPanel){
        super();
        nickname = new MyTextField("Your nickname");
        this.panel.add(nickname);

        password = new MyTextField("Your password");
        this.panel.add(password);
        this.south.setPreferredSize(new Dimension(400,400));

        this.panel.add(MyButton.createBLogin(cardLayout, cardPanel, nickname));
        this.panel.add(MyButton.createBSignUp(cardLayout, cardPanel));
    }


}
