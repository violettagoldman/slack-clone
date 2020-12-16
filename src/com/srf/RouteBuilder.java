package com.srf;

import com.controllers.Controller;
import com.srf.decorators.ControllerRoute;
import com.srf.decorators.MethodRoute;
import com.srf.routes.RouteItem;
import com.srf.routes.RouteTree;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class RouteBuilder {
    private static Set<Class<?>> controllers;
    private static RouteTree routeTree;
    private Reflections reflections = new Reflections("com.controllers");
    public RouteBuilder(){
    }
    public ArrayList<RouteItem> buildControllerRouteItems(Class<Controller> method){
        String route = method.getAnnotation(MethodRoute.class).route();
     return null;
    }
    public RouteTree buildRouteTree(){
        controllers= reflections.getTypesAnnotatedWith(ControllerRoute.class);
        return null;
    }
    public static void main(String[] args) {
    }
}
