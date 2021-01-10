package pijakogui.compoment;

import pijakogui.panel.MyButton;

import javax.swing.*;
import java.awt.*;

public class ToolsBars extends JPanel {
    public ToolsBars(CardLayout cardLayout, JPanel cardPanel, CardLayout cardLayout2, JPanel cardPanel2){
        this.setLayout(new FlowLayout());
        this.setBackground(MyColor.black());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,new Color(101, 162, 201)));
        this.add( MyButton.createBProfile( cardLayout, cardPanel) );
        this.add( MyButton.createBChannels(cardLayout, cardPanel) );
        this.add( MyButton.createBNewChannel(cardLayout, cardPanel));
        this.add( MyButton.createBLogOut());
    }

}
