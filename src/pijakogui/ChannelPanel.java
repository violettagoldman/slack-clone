package pijakogui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChannelPanel extends JPanel {
    private final String title;

    public String getTitle() { return title; }

    public String getId() { return id; }

    public JPanel getMessagesZone() { return messagesZone; }

    public JPanel getListUser() {return listUser; }

    public HashMap<String, MyButton> getUsersMap() {return usersMap; }

    private final String id;
    private final JPanel messagesZone;
    private final JPanel listUser;
    private final HashMap<String, MyButton> usersMap = new HashMap<>();
    private final  JScrollPane scrollMessages;

    public ChannelPanel(String title, String id, String user){
        this.title = title;
        this.id = id;
        this.setLayout(new BorderLayout());

        //Zone menu et titre
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu.add(MyButton.createBNameChannel(title));
        menu.setBackground(MyColor.black());
        menu.setPreferredSize(new Dimension(0,40));

        //zone de liste des utilisateurs
        listUser = new JPanel();
        listUser.setPreferredSize(new Dimension(100,0));
        listUser.setBackground(MyColor.grayBlue());
        listUser.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0,new Color(101, 162, 201)));
        this.add(listUser , BorderLayout.EAST );

        //Ajout nouvel utilisateur dans la conversation
        JTextField addUser = new MyTextField("add user in this channel");
        menu.add(addUser);
        menu.add(MyButton.createBAddUser(addUser, listUser));
        this.add(menu, BorderLayout.NORTH);

        //Zone des messages
        JPanel containerMessage = new JPanel();
        containerMessage.setLayout(new BorderLayout());
        messagesZone = new JPanel();
        messagesZone.setLayout(new BoxLayout(messagesZone, BoxLayout.PAGE_AXIS));
        messagesZone.setBackground(MyColor.gray());
        messagesZone.add(new MyTextArea("\n\n Channel of "+user+": \n\n**************************************\n\n "
                +title+
                " \n\n**************************************\n\n Send news messages !! "+
                "\n\n**************************************\n\n See the other users at right ->\n\n"
        ));
        containerMessage.add(messagesZone, BorderLayout.CENTER);
        JPanel smiley = new Smiley(messagesZone,title );
        smiley.setVisible(false);
        containerMessage.add(smiley, BorderLayout.SOUTH);
        scrollMessages = MyScroll.createGray(containerMessage);
        this.add(scrollMessages , BorderLayout.CENTER );

        //Zone de saisie des messages
        JPanel write = new JPanel();
        write.setPreferredSize(new Dimension(0,60));
        write.setLayout(new BorderLayout());
        JTextArea writeScript = new JTextArea();
        writeScript.setLineWrap(true);
        JScrollPane scrollWrite = MyScroll.createBlack(writeScript);
        write.add(scrollWrite, BorderLayout.CENTER);
        writeScript.setBackground(MyColor.black());
        writeScript.setFont(new Font("SansSerif", Font.PLAIN, 15));
        writeScript.setForeground(MyColor.white());
        write.add(MyButton.createBSend(writeScript, messagesZone, title), BorderLayout.EAST );
        write.add(MyButton.createBSeeSmile(smiley,"smileybutton/smile.png"), BorderLayout.WEST );
        this.add(write, BorderLayout.SOUTH );
    }

    public JPanel messagesStructure(String nickname){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        //utilisateur et avatar
        JPanel userPlace = new JPanel();
        userPlace.setLayout(new BorderLayout());
        userPlace.setBackground(MyColor.gray());

        JTextArea user = MyTextArea.user(nickname+" :");
        user.setForeground(MyColor.blue());
        user.setPreferredSize(new Dimension(0,25));
        ImageIcon image = new ImageIcon( MyButton.class.getResource("avatar/0.png"));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2);
        userPlace.add(jlabel, BorderLayout.WEST);
        userPlace.add(user, BorderLayout.CENTER);

        //date
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date myDate = new Date();
        JTextArea date = MyTextArea.date(format.format(myDate));

        //zone information
        JPanel north = new JPanel();
        north.setLayout(new GridLayout(0,1));
        north.add(userPlace);
        north.add(date);
        north.setBackground(MyColor.gray());
        north.setPreferredSize(new Dimension(0, 50));
        panel.add(north, BorderLayout.NORTH);

        //zone de suppression message
        JPanel zoneButton = new JPanel();
        zoneButton.setPreferredSize(new Dimension(17,0));
        JButton bDeleteMessages = MyButton.createBDeleteMessage(messagesZone, panel);
        zoneButton.add(bDeleteMessages);
        zoneButton.setBackground(MyColor.gray());
        panel.add(zoneButton, BorderLayout.WEST);

        panel.setBackground(MyColor.gray());
        panel.setBorder(new EmptyBorder(2, 10, 0, 0));

        return panel;
    }

    public void messages(String str, String nickname){
        JPanel messageStruct = messagesStructure(nickname);
        JTextArea message = MyTextArea.message(str);
        message.setForeground(MyColor.white());
        messageStruct.add(message, BorderLayout.CENTER);
        messagesZone.add(messageStruct);
        messagesZone.validate();
        scrollMessages.validate();
        downVerticalScroll();
    }

    public void connected(String user){
        JTextArea connected = new MyTextArea(user+" connected");
        connected.setForeground(MyColor.blue());
        messagesZone.add(connected);
    }

    public void smiley(String smiley, String nickname){
        JPanel messageStruct = messagesStructure(nickname);
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2);
        jlabel.setPreferredSize(new Dimension(30,30));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jlabel, BorderLayout.WEST);
        panel.setBackground(MyColor.gray());
        messageStruct.add(panel, BorderLayout.CENTER);
        messagesZone.add(messageStruct);

        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(0,30));
        south.setBackground(MyColor.gray());
        messageStruct.add(south, BorderLayout.SOUTH);

        messagesZone.validate();
        scrollMessages.validate();
        downVerticalScroll();
    }

    public void updateLisUser(String [] users){
        for (Map.Entry mapentry : usersMap.entrySet()) {
             listUser.remove((Component) mapentry.getValue());
             System.out.println(mapentry.getKey());
             this.validate();
        }
        usersMap.clear();
        for (String user : users) {
            MyButton button = MyButton.createBNameUser(user);
            usersMap.put(user, button);
            listUser.add(button);
        }
        this.listUser.validate();
        this.validate();
    }

    public void downVerticalScroll(){
        scrollMessages.getVerticalScrollBar().setValue(scrollMessages.getVerticalScrollBar().getMaximum());
    }
}
