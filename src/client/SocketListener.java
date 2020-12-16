package client;

public interface SocketListener {
    void onDisconnection(SocketManager sm);

    void onMessage(SocketManager sm, Payload payload);
}