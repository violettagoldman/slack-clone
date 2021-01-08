package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import network.Payload.Type;
import pijakogui.helpers.RandomString;
import pijakogui.invoker.Invoker;
import pijakogui.panel.PijakoWindow;
import pijakogui.services.ChannelsService;
import pijakogui.services.ServiceRoute;

public class Client implements SocketListener, Runnable {
    private SocketManager sm;
    private String user;
    private Thread thread;
    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Payload> payloads = new LinkedBlockingQueue<>();
    public static final Client client = new Client();
    private String channel = "Team Violetta";
    public static final String instanceID = (new RandomString()).nextString();

    public Client(String user) {
        this.user = user;
        start();
    }
    
    public Client() {
        start();
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
            //Socket socket = new Socket("135.181.151.73", 6868);
            Socket socket = new Socket("localhost", 6868);
            sm = new SocketManager(socket, this);
            thread = new Thread(sm);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void sendChannel(String channel) {
        System.out.println(channel);
        Payload payload = new Payload(Type.CHANNEL);
        payload.addProperty("channel", channel);
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
        new PijakoWindow().setVisible(true);
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
            case MESSAGE:
                if (payload.getProps().get("smile").equals("true")) {
                    ChannelsService.addSmiley(payload.getProps().get("message"), payload.getProps().get("user"), payload.getProps().get("channel"), payload.getProps().get("avatar"));
                } else {
                    System.out.println(payload.getProps().get("user") + ": " + payload.getProps().get("message"));
                    ChannelsService.addMessage(payload.getProps().get("message"), payload.getProps().get("user"), payload.getProps().get("channel"),payload.getProps().get("avatar"));
                    payloads.add(payload);
                }
                break;
            case ACTIVE_USERS:
                System.out.println(payload.toString());
                if (payload.getProps().get(this.channel) != null) {
                    String users[] = payload.getProps().get(this.channel).split("\2");
                    if (users != null && users.length != 0)
                    ChannelsService.updateUsersConnected(users, this.channel);
                }
                break;
            case HTTP:
                if(payload.getSenderID().equals(instanceID) && payload.getResponse()!= null){
                    ServiceRoute.invokeService(payload);
                }
        }
    }

}