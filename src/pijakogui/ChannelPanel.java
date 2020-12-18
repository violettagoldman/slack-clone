package pijakogui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChannelPanel extends JPanel {
    private final String title;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public JPanel getMessagesZone() {
        return messagesZone;
    }

    public JPanel getListUser() {
        return listUser;
    }

    public HashMap<String, MyButton> getUsersMap() {
        return usersMap;
    }

    private final String id;
    private final JPanel messagesZone;
    private final JPanel listUser;
    private final HashMap<String, MyButton> usersMap = new HashMap<>();

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
        JTextField addUser = new JTextField("add user in this channel");
        addUser.setBorder(new RoundedBorderCorner());
        addUser.setBackground(new Color(50, 50, 50));
        addUser.setFont(new Font("Nirmala UI Semilight", 0, 12));
        addUser.setForeground(new Color(250,250,250));
        addUser.setPreferredSize(new Dimension(200, 30));
        menu.add(addUser);
        menu.add(MyButton.createBAddUser(addUser, listUser));
        this.add(menu, BorderLayout.NORTH);

        //Zone des messages
        messagesZone = new JPanel();
        messagesZone.setLayout(new BoxLayout(messagesZone, BoxLayout.PAGE_AXIS));
        messagesZone.setBackground(MyColor.gray());
        messagesZone.add(new MyTextArea("\n\n Channel of "+user+": \n\n**************************************\n\n "
                +title+
                " \n\n**************************************\n\n Send news messages !! "+
                "\n\n**************************************\n\n See the other users at right ->\n\n"
        ));
        JScrollPane scrollMessages = MyScroll.createGray(messagesZone);
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
        write.add(MyButton.createBSend(writeScript, messagesZone), BorderLayout.EAST );
        write.add(MyButton.createBSmile(messagesZone), BorderLayout.WEST );

        this.add(write, BorderLayout.SOUTH );
    }

    public void messages(String str, String nickname){
        JTextArea user = new MyTextArea(nickname);
        user.setForeground(MyColor.blue());
        messagesZone.add(user);
        JTextArea message = new MyTextArea(str);
        message.setForeground(MyColor.white());
        messagesZone.add(message);
    }

    public void connected(String user){
        JTextArea connected = new MyTextArea(user+" connected");
        connected.setForeground(MyColor.blue());
        messagesZone.add(connected);
    }

    public void smiley(String smiley, String nickname){
        JTextArea user = new MyTextArea(nickname);
        user.setForeground(MyColor.blue());
        messagesZone.add(user);
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2);
        jlabel.setPreferredSize(new Dimension(20,20));
        messagesZone.add(jlabel);
        messagesZone.validate();
    }

    public void updateLisUser(String [] users){
        for (Map.Entry mapentry : usersMap.entrySet()) {
             listUser.remove((Component) mapentry.getValue());
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
}
