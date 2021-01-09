package com.invoker;

import com.invoker.tree.RouteTree;
import javassist.NotFoundException;

public class Invoker {
    private RouteTree routeTree;
    private Invoker(){
        routeTree = RouteBuilder.buildRouteTree();
    }
    public Object invoke(String route, Object ...args) throws Exception{
        return routeTree.find(route).getMethod().invoke(null, args);

    }
    public static Invoker getInstance(){
        return Instance.INSTANCE;
    }
    private static class Instance{
        private static final Invoker INSTANCE = new Invoker();
    }
}
