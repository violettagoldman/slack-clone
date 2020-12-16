package PijakoGUI;

import javax.swing.*;
import java.awt.*;

public class PijakoWindow extends JFrame {

    public PijakoWindow() {
        super( "Pijako" );

        try {
            UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
            SwingUtilities.updateComponentTreeUI( this);
        } catch( Exception exception ) {
            exception.printStackTrace();
        }
        this.setUndecorated(false);
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setSize( 500, 500 );
        this.setLocationRelativeTo( null );
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pijakoIconWhite.png")));

        final JPanel window = (JPanel) this.getContentPane();
        final CardLayout cardWindow = new CardLayout();
        final CardLayout cardHome = new CardLayout();
        window.setLayout(cardWindow);
        JPanel home = new JPanel();
        JPanel menu = new JPanel();
        menu.setLayout(new BorderLayout());
        home.setLayout(cardHome);
        home.add(new ChannelsPanel(), "channels");
        menu.add(new ToolsBars(cardHome, home, cardWindow, window), BorderLayout.NORTH);
        menu.add(home, BorderLayout.CENTER);
        window.add(MyPanel.login(cardWindow , window), "login");
        window.add(MyPanel.signIn(cardWindow , window), "sign in");

        window.add(menu, "menu");
        cardWindow.show(window, "login");
        cardHome.show(home, "channels");

        JPanel profile = new JPanel();
        home.add(MyPanel.profile(), "profile");
        home.add(MyPanel.newChannel(cardHome, home), "new channel");

        //Font f = Font.createFont(Font.TRUETYPE_FONT, urlfs);
        //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //on l'ajoute a l'envirronement
        //ge.registerFont(f);

    }
}
