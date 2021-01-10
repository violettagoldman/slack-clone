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
        MESSAGE_POST,
        CHANNEL_ADD_USER,
        USER_EDIT_ICONE,
        MESSAGE_DELETE, CHANNEL_REMOVE_USER

    }
    private Type type;
    private RequestType requestType;
    private Map<String, String> props = new ConcurrentHashMap<>();
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

    public ResponseMessage<Object> getResponse() {
        return response;
    }

    public void setResponse(ResponseMessage<Object> response) {
        this.response = response;
    }

    public List<Object> getArgs() {
        return args;
    }


    public Payload(Type type, RequestType requestType){
        this.type = type;
        this.requestType=requestType;
    }

    public Payload(Type type) {
        this.type = type;
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
    public Payload clone(){
        Payload payload = new Payload(this.type);
        payload.setRoute(this.route);
        payload.requestType=requestType;
        payload.props = this.props;
        payload.args = this.args;
        payload.response= this.response;
        payload.senderID= this.senderID;
        payload.route= this.route;
        return payload;
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
