package PijakoGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel {
    private JPanel panel;
    private JPanel south;
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

        JPanel north = new JPanel();
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

    public static MyScroll profile(){
        MyPanel profile = new MyPanel();
        JTextField mail = new JTextField("mail@gmail.com");
        mail.setBorder(new RoundedBorderCorner());
        mail.setBackground(new Color(50, 50, 50));
        mail.setFont(new Font("Nirmala UI Semilight", 0, 15));
        mail.setForeground(new Color(250,250,250));
        mail.setEditable(false);
        profile.panel.add(mail);

        JTextField nickName = new JTextField("nickname");
        nickName.setBorder(new RoundedBorderCorner());
        nickName.setBackground(new Color(50, 50, 50));
        nickName.setFont(new Font("Nirmala UI Semilight", 0, 15));
        nickName.setForeground(new Color(250,250,250));
        profile.panel.add(nickName);

        profile.panel.add(MyButton.createBSaveNickName());
        profile.panel.add(MyButton.createBDeleteAccount());

        MyScroll scroll = MyScroll.createBlack(profile);
        return scroll;
    }

    public static MyScroll signIn(CardLayout cardLayout, JPanel cardPanel ){
        MyPanel profile = new MyPanel();
        JTextField mail = new JTextField("mail@gmail.com");
        mail.setBorder(new RoundedBorderCorner());
        mail.setBackground(new Color(50, 50, 50));
        mail.setFont(new Font("Nirmala UI Semilight", 0, 15));
        mail.setForeground(new Color(250,250,250));
        mail.setEditable(false);
        profile.panel.add(mail);

        JTextField nickName = new JTextField("nickname");
        nickName.setBorder(new RoundedBorderCorner());
        nickName.setBackground(new Color(50, 50, 50));
        nickName.setFont(new Font("Nirmala UI Semilight", 0, 15));
        nickName.setForeground(new Color(250,250,250));
        profile.panel.add(nickName);

        profile.panel.add(MyButton.createBNewProfile( cardLayout, cardPanel));
        profile.panel.add(MyButton.createBGoToConnect( cardLayout, cardPanel));

        MyScroll scroll = MyScroll.createBlack(profile);
        return scroll;
    }

    public static MyScroll newChannel(CardLayout cardLayout, JPanel cardPanel){
        MyPanel newChannel = new MyPanel();

        JTextField title = new JTextField("Name of new channel");
        title.setBorder(new RoundedBorderCorner());
        title.setBackground(new Color(50, 50, 50));
        title.setFont(new Font("Nirmala UI Semilight", 0, 15));
        title.setForeground(new Color(250,250,250));
        newChannel.panel.add(title);
        newChannel.south.setPreferredSize(new Dimension(400,400));

        newChannel.panel.add(MyButton.createBSaveChannel(cardLayout,cardPanel, title));

        MyScroll scroll = MyScroll.createBlack(newChannel);
        return scroll;
    }

    public static MyScroll login(CardLayout cardLayout, JPanel cardPanel){
        MyPanel profile = new MyPanel();


        JTextField nickname = new JTextField("Your nickname");
        nickname.setBorder(new RoundedBorderCorner());
        nickname.setBackground(new Color(50, 50, 50));
        nickname.setFont(new Font("Nirmala UI Semilight", 0, 15));
        nickname.setForeground(new Color(250,250,250));
        profile.panel.add(nickname);

        JTextField password = new JTextField("Your password");
        password.setBorder(new RoundedBorderCorner());
        password.setBackground(new Color(50, 50, 50));
        password.setFont(new Font("MT Extra", 0, 15));
        password.setForeground(new Color(250,250,250));
        profile.panel.add(password);
        profile.south.setPreferredSize(new Dimension(400,400));

        profile.panel.add(MyButton.createBLogin(cardLayout, cardPanel));
        profile.panel.add(MyButton.createBSignIn(cardLayout, cardPanel));

        MyScroll scroll = MyScroll.createBlack(profile);
        return scroll;
    }
}
