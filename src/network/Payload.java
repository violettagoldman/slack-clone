package network;

import com.bean.ResponseMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Payload implements Serializable {
    public enum Type implements Serializable{
        MESSAGE,
        CONNECTION,
        DISCONNECTION,
        ACTIVE_USERS,
        CHANNEL,
        HTTP
    }
    public enum RequestType implements Serializable {
        USER_LOGIN,
        USER_SIGNUP,
        USER_EDIT,
        USER_DELETE,
        USER_FIND,
        CHANNEL_FINDALL,
        CHANNEL_EDIT,
        CHANNEL_DELETE,
        CHANNEL_MESSAGES,
        CHANNEL_CREATE,
        MESSAGE_POST
    }
    private final Type type;
    private RequestType requestType;
    private final Map<String, String> props = new ConcurrentHashMap<>();
    private List<Object> args = new ArrayList<>();
    private ResponseMessage<Object> response;
    private String senderID;
    private String route;


    public void setArgs(List<Object> args) {
        this.args = args;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public ResponseMessage<?> getResponse() {
        return response;
    }

    public void setResponse(ResponseMessage<Object> response) {
        this.response = response;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void addArgs(Object arg) {
        this.args.add(arg);
    }


    public Payload(Type type, RequestType requestType){
        this.type = type;
        this.requestType=requestType;
    }

    public Payload(Type type) {
        this.type = type;
    }

    public Payload(String payload) {
        String lines[] = payload.split("\1");
        this.type = Type.valueOf(lines[0]);
        for (int i = 1; i < lines.length; ++i) {
            String line[] = lines[i].split("=", 2);
            addProperty(line[0], line[1]);
        }
    }

    public void addProperty(String key, String value) {
        props.put(key, value);
    }

    @Override
    public String toString() {
        String result = "";

        result += this.type + "\1";
        for (String key : props.keySet())
            result += key + "=" + props.get(key) + "\1";
        return (result);
    }

    public Type getType() {
        return (type);
    }
    public Map<String, String> getProps() {
        return (new HashMap<>(props));
    }
}

/*
type
message=Hello
channel=general
...
*/


/*
RequestObject:{
    url: /get/channel/all
    args:[Object] = 3
}
onMessage(){
    Object response =  Invoker(/get/users/all,args) =
    payload = serialize(response)
    payload send:
}

*
 Client -> requestobject: objet
*
*
*
* */

/**

 User:
 []: Channel
 []:message
 */