package network;

public interface SocketListener {
    void onDisconnection(SocketManager sm);

    void onMessage(SocketManager sm, Payload payload);
    void onMessage(SocketManager sm, Request request);
}