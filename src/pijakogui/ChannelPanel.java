package pijakogui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChannelPanel extends JPanel {
    public final String title;
    public final String id;
    public final JPanel messagesZone;

    public ChannelPanel(String title, String id){
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
        JPanel listUser = new JPanel();
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
        JTextArea firstMessage = addMessages("\n\n Your channel : \n\n "+title+" \n\n Send news messages !! ");
        firstMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagesZone.add(firstMessage);
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

        this.add(write, BorderLayout.SOUTH );
    }

    public static JTextArea addMessages(String str){
        JTextArea message = new JTextArea();
        message.setEditable(false);
        message.setBackground(MyColor.gray());
        message.setBorder(new EmptyBorder(10, 10, 10, 10));
        message.setLineWrap(true);
        message.setFont(new Font("SansSerif", Font.PLAIN, 15));
        message.setForeground(MyColor.white());
        //RTFEditorKit kit = new RTFEditorKit();
        //message.setContentType("text/rtf");
        //message.setEditorKit(kit);
        //message.setContentType("text/html");
        message.setText(str);
        return message;
    }


}
