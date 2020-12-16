package client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Payload {
    enum Type {
        MESSAGE,
        CONNECTION,
        DISCONNECTION,
    }

    private final Type type;
    private final Map<String, String> props = new ConcurrentHashMap<>();

    public Payload(Type type) {
        this.type = type;
    }

    public Payload(String payload) {
        String lines[] = payload.split("\n");
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

        result += this.type + "\n";
        for (String key : props.keySet())
            result += key + "=" + props.get(key) + "\n";
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