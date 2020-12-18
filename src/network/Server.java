package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 

public class Server implements Runnable, SocketListener {
    private final List<SocketManager> sockets;
    private final ExecutorService pool;
    private final Map<SocketManager, String> activeUsers;

    public Server() {
        sockets = new ArrayList<SocketManager>();
        activeUsers = new HashMap<SocketManager, String>();
        this.pool = Executors.newCachedThreadPool();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(6868)) {
            while (!serverSocket.isClosed()) {
                SocketManager tmp = new SocketManager(serverSocket.accept(), this);
                sockets.add(tmp);
                pool.execute(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            pool.shutdown();
        }
    }

    public void broadcast(Payload payload) {
        for (SocketManager sm : sockets) {
            sm.send(payload);
        }
    }

    public void broadcastActiveUsers() {
        Payload payload = new Payload(Payload.Type.ACTIVE_USERS);
        String users = "";
        for (SocketManager sm : activeUsers.keySet()) {
            users += activeUsers.get(sm) + "\2";
        }
        payload.addProperty("activeUsers", users);
        broadcast(payload);
    }

    public static void main(String[] argv) {
        Server server = new Server();
        server.run();
    }

    @Override
    public void onDisconnection(SocketManager sm) {
        sockets.remove(sm);
        activeUsers.remove(sm);
        broadcastActiveUsers();
        Payload payload = new Payload(Payload.Type.DISCONNECTION);
        broadcast(payload);
    }

    @Override
    public void onMessage(SocketManager sm, Payload payload) {
        broadcast(payload);
        System.out.println(payload.toString());
        if (payload.getType() == Payload.Type.CONNECTION) {
            activeUsers.put(sm, payload.getProps().get("user"));
            broadcastActiveUsers();
        }
    }
}