package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import network.Payload.Type;
import pijakogui.helpers.RandomString;
import pijakogui.panel.PijakoWindow;
import pijakogui.services.ChannelsService;
import pijakogui.ServiceRoute;

public class Client implements SocketListener, Runnable {
    private SocketManager sm;
    private String user;
    private Thread thread;
    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Payload> payloads = new LinkedBlockingQueue<>();
    public static final Client client = new Client();
    private long channel = 0;
    public static final String instanceID = (new RandomString()).nextString();
    private static PijakoWindow window;

    public Client(String user) {
        this.user = user;
        //start();
    }

    public Client() {
        //start();
    }

    public static PijakoWindow getWindow() {
        return window;
    }

    public void setUser(String user) {
        Client cl = Client.getInstance();
        this.user = user;
        cl.sendConnection();
        cl.sendChannel(channel);
    }

    public static Client getInstance() {
        return (client);
    }

    public void start() {
        try {
            Socket socket = new Socket("135.181.151.73", 6868);
            //Socket socket = new Socket("localhost", 6868);
            sm = new SocketManager(socket, this);
            thread = new Thread(sm);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            Payload payload = buildPayloadMessage(message, false, "Team Violetta", "avatar/1.png");
            sm.send(payload);
        }
        scanner.close();
    }

    private Payload buildPayloadMessage(String message, boolean isSmile, String channel, String avatar) {
        Payload payload = new Payload(Payload.Type.MESSAGE);
        payload.addProperty("message", message);
        payload.addProperty("user", user);
        payload.addProperty("smile", isSmile + "");
        payload.addProperty("channel", channel);
        payload.addProperty("avatar", avatar);
        return (payload);
    }

    private Payload buildPayloadConnection() {
        Payload payload = new Payload(Payload.Type.CONNECTION);
        payload.addProperty("user", user);
        return (payload);
    }

    public void sendMessage(String message, String channel , String avatar) {
        // String message = messages.take();
        Payload payload = buildPayloadMessage(message, false, channel, avatar);
        sm.send(payload);
    }

    public void sendSmile(String smile, String channel, String avatar) {
        Payload payload = buildPayloadMessage(smile, true, channel, avatar);
        sm.send(payload);
    }
    public void sendPayload(Payload payload){
        sm.send(payload);
    }
    public void sendChannel(long channel) {
        System.out.println(channel);
        Payload payload = new Payload(Type.CHANNEL);
        payload.addProperty("channel", channel+"");
        this.channel = channel;
        sm.send(payload);
    }

    public void sendConnection() {
        Payload payload = buildPayloadConnection();
        sm.send(payload);
    }

    public void publishMessage(String message) {
       messages.offer(message);
    }

    public static void main(String[] argv) {
        window = new PijakoWindow();
        window.setVisible(true);
        Client cl = Client.getInstance();
        cl.start();
        // cl.sendMessage();
        cl.run();
    }

    @Override
    public void onDisconnection(SocketManager sm) {
        System.out.println("Server disconnected");
    }

    @Override
    public void onMessage(SocketManager sm, Payload payload) {
        switch (payload.getType()) {
            case CONNECTION:
                System.out.println(payload.getProps().get("user") + " connected.");
                break;
            case DISCONNECTION:
                System.out.println(payload.getProps().get("user") + " left.");
                break;
            case ACTIVE_USERS:
                if (payload.getProps().get("channel") != null) {
                    //String users[] = payload.getProps().get(channel).split("\2");
                    //if (users != null && users.length != 0){}
                    //ChannelsService.updateUsersConnected(users, this.channel);
                }
                break;
            case HTTP:
               if(payload.getResponse()==null){
                   return;
               }
              if(payload.getRequestType()== Payload.RequestType.MESSAGE_POST){
                  ChannelsService.addMessage(payload.getResponse());
                break;
              }
              if(payload.getRequestType() == Payload.RequestType.CHANNEL_ADD_USER){
                  if(payload.getResponse().getStatus()<400 ){
                      ServiceRoute.invokeService(payload);
                      break;
                  }
              }
              if(payload.getRequestType() == Payload.RequestType.CHANNEL_REMOVE_USER){
                    if(payload.getResponse().getStatus()<400 ){
                        ServiceRoute.invokeService(payload);
                        break;
                    }
              }
              if(payload.getRequestType() == Payload.RequestType.CHANNEL_DELETE){
                  if(payload.getResponse().getStatus()<400 ){
                     ServiceRoute.invokeService(payload);
                     break;
                  }
              }
              if(payload.getSenderID().equals(instanceID)){
                    ServiceRoute.invokeService(payload);
              }
              break;
            default:

        }
    }

}

/// ______ LES DIFFERENTS CAS DE ON MESSAGE ______________
//connection et Active user ne change pas
//message -> http, un response message broadcasté (avec un message en data)
// attention changer pour smiley, une seule fonction
//inscription -> http, un response uniquement à l'instance qui a envoyé (user dans data)
//connection -> http, un response uniquement à l'instance qui a envoyé (user dans data)
//modification profile, -> http, un response uniquement à l'instance qui a envoyé (user dans data)
//modification icone -> http, un response uniquement à l'instance qui a envoyé (user dans data)
//création d'une chaine -> http, un response uniquement à l'instance qui a envoyé
//ajout d'un utilisateur dans une chaine -> http,
//          -> si data null, un response uniquement à l'instance qui a envoyé
//         -> si data non null, un response broadcasté (un user et une chaine dans la data)
//delete d'un utilisateur dans une chaine -> http,
//          -> si data null, un response uniquement à l'instance qui a envoyé
//         -> si data non null, un response broadcasté (un user et une chaine dans la data)
