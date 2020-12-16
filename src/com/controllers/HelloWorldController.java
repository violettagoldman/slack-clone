package com.controllers;

import com.srf.decorators.ControllerRoute;
import com.srf.decorators.MethodRoute;

@ControllerRoute(route="helloworld")
public class HelloWorldController {
    private  HelloWorldController(){    }

    @MethodRoute(route="print")
    public static void helloWorld(){
        System.out.println("hello world!");
    }
}
