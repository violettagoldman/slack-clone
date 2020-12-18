package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Client implements SocketListener, Runnable {
    private SocketManager sm;
    private String user;
    private Thread thread;
    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Payload> payloads = new LinkedBlockingQueue<>();
    private static final Client client = new Client();

    public Client(String user) {
        this.user = user;
        start();
    }
    
    public Client() {
    }

    public void setUser(String user) {
        this.user = user;
        Client.getInstance().sendConnection();
    }

    public static Client getInstance() {
        return (client);
    }

    public void start() {
        try {
            Socket socket = new Socket("135.181.151.73", 6868);
            // Socket socket = new Socket("localhost", 6868);
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
            // Click send and get message
            Payload payload = buildPayloadMessage(message);
            sm.send(payload);
        }
        scanner.close();
    }

    private Payload buildPayloadMessage(String message) {
        Payload payload = new Payload(Payload.Type.MESSAGE);
        payload.addProperty("message", message);
        payload.addProperty("user", user);
        return (payload);
    }

    private Payload buildPayloadConnection() {
        Payload payload = new Payload(Payload.Type.CONNECTION);
        payload.addProperty("user", user);
        return (payload);
    }

    public void sendMessage() {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        try {
                            String message = messages.take();
                            Payload payload = buildPayloadMessage(message);
                            sm.send(payload);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    System.out.println("Demon end");
                }
            }
        }, "Demon");
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    public void sendConnection() {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // while (true) {
                        try {
                            Payload payload = buildPayloadConnection();
                            sm.send(payload);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    // }
                } finally {
                    System.out.println("Demon end");
                }
            }
        }, "Demon");
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    public void publishMessage(String message) {
        messages.offer(message);
    }

    public static void main(String[] argv) {
        new pijakogui.PijakoWindow().setVisible(true);
        Client cl = Client.getInstance();
        cl.start();
        cl.sendMessage();
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
                // pijakogui.Service.addUser(payload.getProps().get("user"), "Team Violetta");
                break;
            case DISCONNECTION:
                System.out.println(payload.getProps().get("user") + " left.");
                // pijakogui.Service.removeUser(payload.getProps().get("user"), "Team Violetta");
                break;
            case MESSAGE:
                System.out.println(payload.getProps().get("user") + ": " + payload.getProps().get("message"));
                pijakogui.Service.addMessage(payload.getProps().get("message"), payload.getProps().get("user"), "Team Violetta");
                payloads.add(payload);
                break;
            case ACTIVE_USERS:
                System.out.println(payload.toString());
                String users[] = payload.getProps().get("activeUsers").split("\2");
                pijakogui.Service.updateUsersConnected(users, "Team Violetta");
                break;
        }
    }
}