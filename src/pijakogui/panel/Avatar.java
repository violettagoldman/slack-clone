package pijakogui.panel;

import javax.swing.*;
import java.awt.*;

public class Avatar extends MyPanel{
    private String avatarStr;
    public Avatar(String avatarStr){
        super();
        this.avatarStr = avatarStr;
        this.north.remove(0);
        ImageIcon image = new ImageIcon( this.getClass().getResource(avatarStr));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        this.north.add(jlabel, BorderLayout.CENTER);
        this.panel.setLayout(new GridLayout(4,6, 30, 30));

        for(int i = 0; i<20 ; i++){
            this.panel.add(MyButton.createBChooseAvatar("avatar/"+i+".png", this));
            this.panel.validate();
        }
    }

    public String getAvatarStr() {
        return avatarStr;
    }

    public void setAvatarStr(String avatarStr) {
        this.avatarStr = avatarStr;
    }
}
