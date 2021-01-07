package network;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private ArrayList<Object> args;
    private String req;

    public ArrayList<Object> getArgs() {
        return args;
    }
    public void setArgs(ArrayList<Object> args) {
        this.args = args;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }
}
