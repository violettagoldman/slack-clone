package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 

public class Server implements Runnable, SocketListener {
    private final List<SocketManager> sockets;
    private final ExecutorService pool;

    public Server() {
        sockets = new ArrayList<SocketManager>();
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

    public static void main(String[] argv) {
        Server server = new Server();
        server.run();
    }

    @Override
    public void onDisconnection(SocketManager sm) {
        sockets.remove(sm);
        Payload payload = new Payload(Payload.Type.DISCONNECTION);
        broadcast(payload);
    }

    @Override
    public void onMessage(SocketManager sm, Payload payload) {
        System.out.println(payload.toString());
        broadcast(payload);
    }
}