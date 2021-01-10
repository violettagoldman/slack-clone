package pijakogui.panel;


import network.Client;
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
    private static Profile profile;
    private static SignUp signUp;
    private static Login login;
    private static NewChannel newChannel;
    private static Avatar avatar;
    private static JPanel home;
    private static CardLayout cardHome;
    private static CardLayout cardWindow;
    private static ChannelsPanel channelsPanel;


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

        //Paramètre de la fenêtre
        this.setUndecorated(false);
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setSize( 1000, 800 );
        this.setLocationRelativeTo( null );
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pijakoIconWhite.png")));

        //fermeture du programme à la fermeture de la fenetre
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e ){
                System.exit(0);
            };
        });

        //Ajout des éléments à la fenetre
        window = (JPanel) this.getContentPane();
        cardWindow = new CardLayout();
        cardHome = new CardLayout();
        window.setLayout(cardWindow);
        JPanel menu = new JPanel();
        home = new JPanel();
        menu.setLayout(new BorderLayout());
        menu.add(new ToolsBars(cardHome, home, cardWindow, window), BorderLayout.NORTH);
        menu.add(home, BorderLayout.CENTER);
        login = new Login(cardWindow , window);
        window.add(MyScroll.createBlack(login), "login");
        signUp = new SignUp(cardWindow , window);
        window.add(MyScroll.createBlack(signUp), "sign up");
        window.add(menu, "menu");

        //Test ajout de message depuis le serveur
        //ChannelsService.addChannel("Team Violetta", 00021);

    }

    public static void rebuilEnvironment(){
        window.removeAll();
        cardWindow = new CardLayout();
        cardHome = new CardLayout();
        window.setLayout(cardWindow);
        JPanel menu = new JPanel();
        home = new JPanel();
        menu.setLayout(new BorderLayout());
        menu.add(new ToolsBars(cardHome, home, cardWindow, window), BorderLayout.NORTH);
        menu.add(home, BorderLayout.CENTER);
        login = new Login(cardWindow , window);
        window.add(MyScroll.createBlack(login), "login");
        signUp = new SignUp(cardWindow , window);
        window.add(MyScroll.createBlack(signUp), "sign up");
        window.add(menu, "menu");
        cardWindow.show(window, "login");
    }

    public static void updateEnvironment(){
        home.setLayout(cardHome);
        channelsPanel = new ChannelsPanel();
        home.add(channelsPanel, "channels");
        cardWindow.show(window, "login");
        cardHome.show(home, "channels");
        profile = new Profile(cardHome, home);
        home.add(MyScroll.createBlack(profile), "profile");
        avatar = new Avatar(UserService.getUser().getIcone());
        home.add(MyScroll.createBlack(avatar), "avatar");
        newChannel = new NewChannel(cardHome, home);
        home.add(MyScroll.createBlack(newChannel), "new channel");
        seeMenu();
    }

    public static Profile getProfile() {
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

    public static void seeChannels(){
        cardHome.show(home, "channels");
    }

    public static void seeMenu(){
        cardWindow.show(window, "menu");
    }

    public static Avatar getAvatar() {
        return avatar;
    }

    public static ChannelsPanel getChannelsPanel() {
        return channelsPanel;
    }
}
