package pijakogui.requestclient;

import network.Client;
import network.Payload;
import network.Request;
import pijakogui.Main;

import java.util.Arrays;

public class PayloadBuilder {

    public static Payload buildRequest(String route, Payload.RequestType requestType, Object ...args){
        Payload payload= new Payload(Payload.Type.HTTP, requestType);
        payload.setSenderID(Client.instanceID);
        payload.setArgs(Arrays.asList(args));
        payload.setRoute(route);
        return payload;
    }
}
