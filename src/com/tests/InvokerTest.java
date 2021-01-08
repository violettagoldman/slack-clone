package com.tests;

import com.bean.ResponseMessage;
import com.bean.User;
import com.invoker.Invoker;
import com.invoker.tree.RouteItem;
import com.invoker.tree.RouteTree;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class InvokerTest {

    public InvokerTest(){}


    @Test
    public void testTree2(){
        RouteTree rt = new RouteTree(null, "GET");
        rt.insert(new RouteItem("user/getall",null));
        rt.insert(new RouteItem("user/getMy",null));
        rt.insert(new RouteItem("channel/add/one",null));
        System.out.println(rt);
    }

    @Test
    public void invoker(){
        ArrayList<Object> args = new ArrayList<>();
        args.add("mail@gmail.com");
        args.add("nickname");
        args.add("Paulius123");
        args.add("Paulius123");
        Object[] array = args.toArray();
        ResponseMessage res = (ResponseMessage<User>)Invoker.getInstance().invoke("users/signup",array);
        System.out.println(res);
    }

    @Test
    public void invokerWithArg(){
        Object string = Invoker.getInstance().invoke("channel/adduser", 3);
        System.out.println(string);
    }

}
