package pijakogui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String str){
        super(str);
        this.setBackground(MyColor.grayWith());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBorder(null);
        this.setPreferredSize(new Dimension(60,0));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.blue());
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWith());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWith());
            }
        });
    }

    public MyButton(Icon i){
        super(i);
        this.setBackground(MyColor.grayWith());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBorder(null);
        this.setPreferredSize(new Dimension(60,0));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.blue());
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWith());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWith());
            }
        });
    }


    public static MyButton createBSend(JTextArea write, JPanel messagesZone){
        MyButton send = new MyButton("Send");
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (!write.getText().equals("")) {
                    String str = write.getText();
                    //JButton bDeleteMessages = MyButton.createBDeleteMessage(messagesZone, message);
                   // messagesZone.add(bDeleteMessages);
                    messagesZone.validate();
                    network.Client.getInstance().publishMessage(str);
                    write.setText("");
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBSmile(JPanel messagesZone){
        MyButton send = new MyButton(new ImageIcon(MyButton.class.getResource("smile.png")));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                network.Client.getInstance().sendSmile("smile.png");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBNewChannel(CardLayout cardLayout, JPanel cardPanel){
        MyButton bNewChannel = new MyButton("New channel");
        bNewChannel.setPreferredSize(new Dimension(100,20));
        bNewChannel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "new channel");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNewChannel;
    }

    public static MyButton createBProfile(CardLayout cardLayout, JPanel cardPanel){
        MyButton bProfile = new MyButton("Profile");
        bProfile.setPreferredSize(new Dimension(100,20));
        bProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "profile");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bProfile;
    }

    public static MyButton createBChannels(CardLayout cardLayout, JPanel cardPanel){
        MyButton bProfile = new MyButton("Channels");
        bProfile.setPreferredSize(new Dimension(100,20));
        bProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "channels");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bProfile;
    }

    public static MyButton createBSignUp(CardLayout cardLayout, JPanel cardPanel){
        MyButton bSignUp = new MyButton("SignUp");
        bSignUp.setPreferredSize(new Dimension(100,20));
        bSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { cardLayout.show(cardPanel, "login"); }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSignUp;
    }

    public static MyButton createBSaveNickName(){
        MyButton bSaveNickName = new MyButton("Save nickName");
        bSaveNickName.setPreferredSize(new Dimension(100,20));
        bSaveNickName.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSaveNickName;
    }

    public static MyButton createBDeleteAccount(){
        MyButton bDeleteAccount = new MyButton("Delete Account");
        bDeleteAccount.setPreferredSize(new Dimension(100,20));
        bDeleteAccount.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bDeleteAccount;
    }

    public static MyButton createBSaveChannel(CardLayout cardLayout, JPanel cardPanel, JTextField title){
        MyButton bSaveChannel = new MyButton("Create new channel");
        bSaveChannel.setPreferredSize(new Dimension(100,20));
        bSaveChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ChannelsPanel.addChannels(title.getText(), "user");
                title.setText("Name of new channel");
                cardLayout.show(cardPanel, "channels");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSaveChannel;
    }

    public static MyButton createBSignIn(CardLayout cardLayout, JPanel cardPanel){
        MyButton bSignIn = new MyButton("SignIn");
        bSignIn.setPreferredSize(new Dimension(100,20));
        bSignIn.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { cardLayout.show(cardPanel, "sign in");}
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSignIn;
    }

    public static MyButton createBLogin(CardLayout cardLayout, JPanel cardPanel, JTextField nickname){
        MyButton bLogin = new MyButton("Connect");
        bLogin.setPreferredSize(new Dimension(100,20));
        bLogin.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                network.Client.getInstance().setUser(nickname.getText());
                cardLayout.show(cardPanel, "menu");}
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bLogin;
    }

    public static MyButton createBNewProfile(CardLayout cardLayout, JPanel cardPanel){
        MyButton bNewProfile = new MyButton("Save profile");
        bNewProfile.setPreferredSize(new Dimension(100,20));
        bNewProfile.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { cardLayout.show(cardPanel, "menu"); }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNewProfile;
    }

    public static MyButton createBGoToConnect(CardLayout cardLayout, JPanel cardPanel){
        MyButton bGoToConnect = new MyButton("Go to connect");
        bGoToConnect.setPreferredSize(new Dimension(100,20));
        bGoToConnect.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { cardLayout.show(cardPanel, "login"); }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bGoToConnect;
    }

    public static MyButton createBGoChannel( CardLayout cardLayout, JPanel cardPanel , String title){
        MyButton bGoToChannel = new MyButton(title);
        bGoToChannel.setPreferredSize(new Dimension(200,50));
        bGoToChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { cardLayout.show(cardPanel, title);
                 }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bGoToChannel;
    }

    public static MyButton createBNameChannel( String title){
        MyButton bNameChannel = new MyButton(title);
        bNameChannel.setPreferredSize(new Dimension(200,30));
        bNameChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { ;
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNameChannel;
    }


    public static MyButton createBDeleteMessage(JPanel messagesZone, JTextPane message){
        MyButton bDeleteMessage = new MyButton("Delete");
        bDeleteMessage.setPreferredSize(new Dimension(200,20));
        bDeleteMessage.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                messagesZone.remove(message);
                messagesZone.remove(bDeleteMessage);
                messagesZone.validate();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bDeleteMessage;
    }

    public static MyButton createBAddUser(JTextField addUser, JPanel listUser){
        MyButton bAddUser = new MyButton("Add user");
        bAddUser.setPreferredSize(new Dimension(100,30));
        bAddUser.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listUser.add(MyButton.createBNameUser(addUser.getText()));
                addUser.setText("add user in this channel");
                listUser.validate();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bAddUser;
    }


    public static MyButton createBNameUser(String name){
        MyButton bNameUser = new MyButton(name);
        bNameUser.setPreferredSize(new Dimension(100,30));
        bNameUser.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNameUser;
    }


}
