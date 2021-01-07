package network;

import com.bean.ResponseMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Payload {
    public enum Type {
        MESSAGE,
        CONNECTION,
        DISCONNECTION,
        ACTIVE_USERS,
        CHANNEL,
        REQUEST
    }

    private final Type type;
    private final Map<String, String> props = new ConcurrentHashMap<>();
    private List<Object> args = new ArrayList<>();
    private ResponseMessage<Object> response;
    private long from;
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