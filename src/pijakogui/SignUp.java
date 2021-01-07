package pijakogui;

import javax.swing.*;
import java.awt.*;

public class SignUp extends MyPanel{
    private JTextField error;
    private JTextField mail;
    private JTextField nickName;
    private JTextField password1;
    private JTextField password2;

    public SignUp(CardLayout cardLayout, JPanel cardPanel) {
        super();
        JTextField information = MyTextField.BorderEmpty("Password : minimum 8 characters with at least 1 uppercase letter, 1 lowercase letter and 1 number");
        this.panel.add(information);

        error = MyTextField.BorderEmpty("");
        this.panel.add(error);

        mail = new MyTextField("mail@gmail.com");
        this.panel.add(mail);

        nickName = new MyTextField("nickname");
        this.panel.add(nickName);

        password1 = new MyTextField("password");
        this.panel.add(password1);

        password2 = new MyTextField("confirm password");
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
}
