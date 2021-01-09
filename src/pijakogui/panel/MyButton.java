package pijakogui.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import network.Client;
import pijakogui.compoment.MyColor;
import pijakogui.requestclient.ChannelClient;
import pijakogui.requestclient.UserClient;
import pijakogui.services.ChannelsService;
import pijakogui.services.UserService;


public class MyButton extends JButton {
    public MyButton(String str){
        super(str);
        this.setBackground(MyColor.grayWithe());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBorder(null);
        this.setPreferredSize(new Dimension(60,0));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.blue());
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
        });
    }

    public MyButton(Icon i){
        super(i);
        this.setBackground(MyColor.grayWithe());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBorder(null);
        this.setPreferredSize(new Dimension(60,0));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { setBackground(MyColor.blue()); }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
        });
    }


    public static MyButton createBSend(JTextArea write,  String title, String avatar){
        MyButton send = new MyButton("Send");
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (!write.getText().equals("")) {
                    String str = write.getText();
                    network.Client.getInstance().sendMessage(str, title, avatar);
                    write.setText("");
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBSmile( String title, String smiley, String avatar){
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        MyButton send = new MyButton(image2);
        send.setPreferredSize(new Dimension(40,40));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                network.Client.getInstance().sendSmile(smiley, title, avatar);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBSeeSmile(JPanel smileyPanel, String smiley){
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        MyButton send = new MyButton(image2);
        send.setPreferredSize(new Dimension(40,40));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(smileyPanel.isVisible()){
                    smileyPanel.setVisible(false);
                }else {
                    smileyPanel.setVisible(true);
                }
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


    public static MyButton createBSaveInformation(String mail, String nickname, String password){
        MyButton bSaveNickName = new MyButton("Save information");
        bSaveNickName.setPreferredSize(new Dimension(100,20));
        bSaveNickName.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UserClient.updateUser(UserService.getUser(), nickname, mail, password, UserService.getUser().getIcone());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSaveNickName;
    }

    public static MyButton createBDeleteAccount(){
        MyButton bDeleteAccount = new MyButton("Delete Account");
        bDeleteAccount.setPreferredSize(new Dimension(100,20));
        bDeleteAccount.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int response = JOptionPane.showConfirmDialog(Client.getWindow(), "Delete your profile ? If you delete \n" +
                        "if you delete your account, the app will close", "Delete account", JOptionPane.YES_NO_OPTION);
                if(response== JOptionPane.YES_OPTION){ //
                    UserClient.delete(UserService.getUser().getId());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bDeleteAccount;
    }

    public static MyButton createBSaveChannel(CardLayout cardLayout, JPanel cardPanel, JTextField title, long adminId){
        MyButton bSaveChannel = new MyButton("Create new channel");
        bSaveChannel.setPreferredSize(new Dimension(100,20));
        bSaveChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //envoie de channel au serveur
                //network.Client.getInstance().sendChannel(title.getText());
                //ChannelClient.createChannel();
                title.setText("Name of new channel");
                cardLayout.show(cardPanel, "channels");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSaveChannel;
    }

    public static MyButton createBSignUp(CardLayout cardLayout, JPanel cardPanel){
        MyButton bSignIn = new MyButton("SignUp");
        bSignIn.setPreferredSize(new Dimension(100,20));
        bSignIn.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "sign up");}
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSignIn;
    }

    public static MyButton createBLogin(CardLayout cardLayout, JPanel cardPanel, JTextField nickname, JTextField password){
        MyButton bLogin = new MyButton("Connect");
        bLogin.setPreferredSize(new Dimension(100,20));
        bLogin.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                network.Client.getInstance().setUser(nickname.getText());
                UserClient.signIn(nickname.getText(), password.getText());
                cardLayout.show(cardPanel, "menu");}
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bLogin;
    }

    public static MyButton createBNewProfile(JTextField error, JTextField mail, JTextField nickname, JTextField password1, JTextField password2, CardLayout cardLayout, JPanel cardPanel){
        MyButton bNewProfile = new MyButton("Save profile");
        bNewProfile.setPreferredSize(new Dimension(100,20));
        bNewProfile.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                String mailStr = mail.getText();
                String nicknameStr = nickname.getText();
                String password1Str = password1.getText();
                String password2Str = password2.getText();
                UserClient.signUp(nicknameStr,mailStr,password1Str,password2Str);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNewProfile;
    }

    public static MyButton createBGoToConnect(CardLayout cardLayout, JPanel cardPanel){
        MyButton bGoToConnect = new MyButton("Go to connect");
        bGoToConnect.setPreferredSize(new Dimension(100,20));
        bGoToConnect.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "login"); }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bGoToConnect;
    }

    public static MyButton createBGoChannel( CardLayout cardLayout, JPanel cardPanel , String title){
        MyButton bGoToChannel = new MyButton(title);
        bGoToChannel.setPreferredSize(new Dimension(200,50));
        bGoToChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, title);
                network.Client.getInstance().sendChannel(title);
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                network.Client.getInstance().sendChannel(title);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNameChannel;
    }


    public static MyButton createBDeleteMessage(JPanel messagesZone, JPanel message){
        MyButton bDeleteMessage = new MyButton("X");
        bDeleteMessage.setPreferredSize(new Dimension(17,17));
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

    public static MyButton createBNameUserAdmin(String name){
        MyButton bNameUser = new MyButton(name);
        bNameUser.setPreferredSize(new Dimension(100,30));
        bNameUser.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int response = JOptionPane.showConfirmDialog(Client.getWindow(), "Delete this user ?", "Delete user", JOptionPane.YES_NO_OPTION);
                if(response== JOptionPane.YES_OPTION){ //
                    // appeler fonction serveur
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNameUser;
    }

    public static MyButton createBChangeAvatar(CardLayout cardLayout, JPanel cardPanel ){
        MyButton bNameUser = new MyButton("Change avatar");
        bNameUser.setPreferredSize(new Dimension(100,30));
        bNameUser.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "avatar");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNameUser;
    }

    public static MyButton createBChooseAvatar(String avatarStr, Avatar avatar){
        ImageIcon image = new ImageIcon( MyButton.class.getResource(avatarStr));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        MyButton chooseAvatar = new MyButton(image2);
        chooseAvatar.setPreferredSize(new Dimension(80,80));
        chooseAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                avatar.north.removeAll();
                avatar.north.validate();
                avatar.north.add(MyButton.createBCSaveAvatar(), BorderLayout.SOUTH);
                ImageIcon image = new ImageIcon( chooseAvatar.getClass().getResource(avatarStr));
                ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                JLabel jlabel = new JLabel(image2, JLabel.CENTER);
                avatar.north.add(jlabel, BorderLayout.CENTER);
                avatar.north.validate();
                avatar.setAvatarStr(avatarStr);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return chooseAvatar;
    }

    public static MyButton createBCSaveAvatar(){
        MyButton chooseAvatar = new MyButton("Save avatar");
        chooseAvatar.setPreferredSize(new Dimension(20,20));
        chooseAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return chooseAvatar;
    }


}
