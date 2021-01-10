package pijakogui.panel;

import pijakogui.compoment.MyColor;
import pijakogui.services.UserService;

import javax.swing.*;
import java.awt.*;

public class Smiley extends JPanel {
    public Smiley(JPanel messageZone, long  channelID){
        this.setBackground(MyColor.gray());
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(0,100));

        for(int i = 0; i<20 ; i++){
            this.add(MyButton.createBSmile(channelID, "smileybutton/smiley"+i+".png"));
            System.out.println("smileybutton/smiley"+i+".png");
            this.validate();
        }

    }
}
