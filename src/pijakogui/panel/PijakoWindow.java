package pijakogui.panel;


import pijakogui.compoment.MyScroll;
import pijakogui.compoment.ToolsBars;
import pijakogui.services.ChannelsService;
import pijakogui.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
// Classe principale de l'interface
public class PijakoWindow extends JFrame {
    private static JPanel window;
    private static MyPanel profile;
    private static SignUp signUp;
    private static Login login;
    private static NewChannel newChannel;


    public PijakoWindow() {
        super( "Pijako" );

        //layout selon system
        if(System.getProperty("os.name").equals("Windows 10")){
            try {
                UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
                SwingUtilities.updateComponentTreeUI( this);
            } catch( Exception exception ) {
                exception.printStackTrace();
            }
        }else {
            try {
                UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
                SwingUtilities.updateComponentTreeUI( this);
            } catch( Exception exception ) {
                exception.printStackTrace();
            }
        }
        //test---------
        UserService.test();
        //--------------


        //Paramètre de la fenêtre
        this.setUndecorated(false);
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setSize( 500, 500 );
        this.setLocationRelativeTo( null );
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pijakoIconWhite.png")));

        //fermeture du programme à la fermeture de la fenetre
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e ){
                System.exit(1);
            };
        });

        //Ajout des éléments à la fenetre
        window = (JPanel) this.getContentPane();
        final CardLayout cardWindow = new CardLayout();
        final CardLayout cardHome = new CardLayout();
        window.setLayout(cardWindow);
        JPanel menu = new JPanel();
        JPanel home = new JPanel();
        menu.setLayout(new BorderLayout());
        menu.add(new ToolsBars(cardHome, home, cardWindow, window), BorderLayout.NORTH);
        menu.add(home, BorderLayout.CENTER);
        login = new Login(cardWindow , window);
        window.add(MyScroll.createBlack(login), "login");
        signUp = new SignUp(cardWindow , window);
        window.add(MyScroll.createBlack(signUp), "sign up");
        window.add(menu, "menu");


        home.setLayout(cardHome);
        home.add(new ChannelsPanel(), "channels");
        cardWindow.show(window, "login");
        cardHome.show(home, "channels");
        profile = new Profile(cardHome, home);
        home.add(MyScroll.createBlack(profile), "profile");
        home.add(MyPanel.avatar("avatar/0.png"), "avatar");
        newChannel = new NewChannel(cardHome, home);
        home.add(MyScroll.createBlack(newChannel), "new channel");

        //Test ajout de message depuis le serveur
        ChannelsService.addChannel("Team Violetta", 00021);

    }



    public static MyPanel getProfile() {
        return profile;
    }

    public static SignUp getSignUp() {
        return signUp;
    }

    public static Login getLogin() {
        return login;
    }

    public static NewChannel getNewChannel() {
        return newChannel;
    }
}
