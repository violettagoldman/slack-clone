package network;

import com.bean.ResponseMessage;
import com.invoker.Invoker;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 

public class Server implements Runnable, SocketListener {
    private final List<SocketManager> sockets;
    private final ExecutorService pool;
    private final Map<SocketManager, network.User> activeUsers;
    private final Set<String> channels;

    public Server() {
        sockets = new ArrayList<SocketManager>();
        activeUsers = new HashMap<SocketManager, network.User>();
        this.pool = Executors.newCachedThreadPool();
        this.channels = new HashSet<String>();
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
        for (String ch : channels) {
            String users = "";
            for (SocketManager sm : activeUsers.keySet()) {
                if (activeUsers.get(sm) != null && activeUsers.get(sm).getChannel() != null && activeUsers.get(sm).getChannel().equals(ch)) {
                    users += activeUsers.get(sm).getName() + "\2";
                }
            }
            payload.addProperty(ch, users);   
        }
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
            activeUsers.put(sm, new network.User(payload.getProps().get("user")));
            broadcastActiveUsers();
        }
        if (payload.getType() == Payload.Type.CHANNEL && activeUsers.get(sm) != null) {
            activeUsers.get(sm).setChannel(payload.getProps().get("channel"));
            channels.add(payload.getProps().get("channel"));
            broadcastActiveUsers();
        }
        if(payload.getType()==Payload.Type.HTTP){
            try{
                ResponseMessage<Object> res= (ResponseMessage<Object>) Invoker.getInstance().invoke(payload.getProps().get("route"), 5 );
                payload.setResponse(res);
                broadcast(payload);
            }catch(Exception e){

            }
        }
    }
    public void onMessage(SocketManager sm, Request payload) {
        Object response = Invoker.getInstance().invoke(payload.getReq(),payload.getArgs());
        sm.send(response);
    }
}