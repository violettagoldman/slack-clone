package pijakogui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField (String str){
        super(str);
        this.setBorder(new RoundedBorderCorner());
        this.setBackground(new Color(50, 50, 50));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));
        this.setForeground(new Color(250,250,250));
    }

    public static MyTextField BorderEmpty(String str){
        MyTextField text = new MyTextField(str);
        text.setBorder(new EmptyBorder(0,0,0,0));
        text.setEditable(false);
        return text;
    }

}
