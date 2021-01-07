package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import network.Payload.Type;
import pijakogui.Main;
import pijakogui.invoker.Invoker;
import pijakogui.services.Service;

public class Client implements SocketListener, Runnable {
    private SocketManager sm;
    private String user;
    private Thread thread;
    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Payload> payloads = new LinkedBlockingQueue<>();
    public static final Client client = new Client();
    private String channel = "Team Violetta";

    public Client(String user) {
        this.user = user;
        start();
    }
    
    public Client() {
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
            Payload payload = buildPayloadMessage(message, false, "Team Violetta");
            sm.send(payload);
        }
        scanner.close();
    }

    private Payload buildPayloadMessage(String message, boolean isSmile, String channel) {
        Payload payload = new Payload(Payload.Type.MESSAGE);
        payload.addProperty("message", message);
        payload.addProperty("user", user);
        payload.addProperty("smile", isSmile + "");
        payload.addProperty("channel", channel);
        return (payload);
    }

    private Payload buildPayloadConnection() {
        Payload payload = new Payload(Payload.Type.CONNECTION);
        payload.addProperty("user", user);
        return (payload);
    }

    public void sendMessage(String message, String channel) {
        // String message = messages.take();
        Payload payload = buildPayloadMessage(message, false, channel);
        sm.send(payload);
    }

    public void sendSmile(String smile, String channel) {
        Payload payload = buildPayloadMessage(smile, true, channel);
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
        new pijakogui.PijakoWindow().setVisible(true);
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
                    Service.addSmiley(payload.getProps().get("message"), payload.getProps().get("user"), payload.getProps().get("channel"));
                } else {
                    System.out.println(payload.getProps().get("user") + ": " + payload.getProps().get("message"));
                    Service.addMessage(payload.getProps().get("message"), payload.getProps().get("user"), payload.getProps().get("channel"));
                    payloads.add(payload);
                }
                break;
            case ACTIVE_USERS:
                System.out.println(payload.toString());
                if (payload.getProps().get(this.channel) != null) {
                    String users[] = payload.getProps().get(this.channel).split("\2");
                    if (users != null && users.length != 0)
                    Service.updateUsersConnected(users, this.channel);
                }
                break;
            case HTTP:
                if(!payload.getSenderID().equals(Main.instanceID)){
                    return;
                }
                Invoker.getInstance().invoke(payload.get)

        }
    }
    public void onMessage(SocketManager sm, Request payload) {

    }

}