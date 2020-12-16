package com.controllers;

import com.invoker.decorators.ControllerRoute;
import com.invoker.decorators.MethodRoute;

@ControllerRoute("channel")
public class ChannelController extends Controller {
    private ChannelController(){    }

    @MethodRoute("getAll")
    public static void helloWorld(){
        System.out.println("des channels");
    }
    // /channel/getByID

    @MethodRoute("adduser")
    public static String helloWorldArg(int arg){
        return "Channel: " + arg;
    }

}

