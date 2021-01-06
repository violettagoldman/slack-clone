package pijakogui;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private JPanel panel;
    private JPanel south;
    private JPanel north;
    private static String avatarChoose;
    private MyPanel(){
        this.setBackground(MyColor.black());
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(0,1, 60, 60));
        panel.setBackground(MyColor.black());
        this.add(panel, BorderLayout.CENTER);

        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(100,100));
        this.add(west, BorderLayout.WEST);
        west.setBackground(MyColor.black());

        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(100,100));
        east.setBackground(MyColor.black());
        this.add(east, BorderLayout.EAST);

        north = new JPanel();
        north.setPreferredSize(new Dimension(100,100));
        north.setBackground(MyColor.black());
        north.setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon( getClass().getResource( "pijakoIconWhite.png"));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(50, 55, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        north.add(jlabel, BorderLayout.CENTER);

        this.add(north, BorderLayout.NORTH);

        south = new JPanel();
        south.setPreferredSize(new Dimension(100,100));
        south.setBackground(MyColor.black());
        this.add(south, BorderLayout.SOUTH);

    }

    public static void setAvatarChoose(String str){
        avatarChoose = str;
    }

    public static MyScroll profile(CardLayout cardLayout, JPanel cardPanel){
        MyPanel profile = new MyPanel();

        ImageIcon image = new ImageIcon( profile.getClass().getResource( "avatar/0.png"));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        profile.panel.add(jlabel);

        profile.panel.add(MyButton.createBChangeAvatar(cardLayout, cardPanel));

        JTextField mail = new MyTextField("mail@gmail.com");
        profile.panel.add(mail);

        JTextField nickName = new MyTextField("nickname");
        profile.panel.add(nickName);


        JTextField password = new MyTextField("password");
        profile.panel.add(password);

        profile.panel.add(MyButton.createBSaveInformation());

        profile.panel.add(MyButton.createBDeleteAccount());

        MyScroll scroll = MyScroll.createBlack(profile);
        return scroll;
    }

    public static MyScroll signUp(CardLayout cardLayout, JPanel cardPanel ){
        MyPanel signIn = new MyPanel();
        JTextField mail = new MyTextField("mail@gmail.com");
        mail.setEditable(false);
        signIn.panel.add(mail);

        JTextField nickName = new MyTextField("nickname");
        signIn.panel.add(nickName);

        signIn.panel.add(MyButton.createBNewProfile( cardLayout, cardPanel));
        signIn.panel.add(MyButton.createBGoToConnect( cardLayout, cardPanel));

        MyScroll scroll = MyScroll.createBlack(signIn);
        return scroll;
    }

    public static MyScroll newChannel(CardLayout cardLayout, JPanel cardPanel){
        MyPanel newChannel = new MyPanel();

        JTextField title = new MyTextField("Name of new channel");
        newChannel.panel.add(title);
        newChannel.south.setPreferredSize(new Dimension(400,400));

        newChannel.panel.add(MyButton.createBSaveChannel(cardLayout,cardPanel, title));

        MyScroll scroll = MyScroll.createBlack(newChannel);
        return scroll;
    }

    public static MyScroll login(CardLayout cardLayout, JPanel cardPanel){
        MyPanel login = new MyPanel();


        JTextField nickname = new MyTextField("Your nickname");
        login.panel.add(nickname);

        JTextField password = new MyTextField("Your password");
        login.panel.add(password);
        login.south.setPreferredSize(new Dimension(400,400));

        login.panel.add(MyButton.createBLogin(cardLayout, cardPanel, nickname));
        login.panel.add(MyButton.createBSignUp(cardLayout, cardPanel));

        MyScroll scroll = MyScroll.createBlack(login);
        return scroll;
    }

    public static MyScroll avatar(String avatarStr){
        MyPanel avatar = new MyPanel();
        avatar.north.remove(0);
        ImageIcon image = new ImageIcon( avatar.getClass().getResource(avatarStr));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        avatar.north.add(jlabel, BorderLayout.CENTER);
        avatar.panel.setLayout(new GridLayout(4,6, 30, 30));

        for(int i = 0; i<20 ; i++){
            avatar.panel.add(MyButton.createBChooseAvatar("avatar/"+i+".png", avatar.north));
            avatar.panel.validate();
        }
        MyScroll scroll = MyScroll.createBlack(avatar);
        return scroll;
    }
}
