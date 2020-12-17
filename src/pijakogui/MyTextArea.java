package pijakogui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyTextArea extends JTextArea {
    public MyTextArea(String str){
        super(str);
        this.setEditable(false);
        this.setBackground(MyColor.gray());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLineWrap(true);
        this.setFont(new Font("SansSerif", Font.PLAIN, 15));
        this.setForeground(MyColor.white());
    }
}
