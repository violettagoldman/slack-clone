package pijakogui.requestclient;

import network.Payload;

public class PayloadBuilder {

    public static Payload buildRequest(String route, Object ...args){
        Payload payload= new Payload(Payload.Type.HTTP);
        for ( Object arg : args) {
            payload.addArgs(arg);
        }
        return payload;
    }
}
