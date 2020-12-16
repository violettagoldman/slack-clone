package com.invoker;

import com.invoker.tree.RouteItem;
import com.invoker.tree.RouteTree;
import javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.xml.bind.SchemaOutputResolver;

public class Invoker {
    private RouteTree routeTree;
    private Invoker(){
        routeTree = RouteBuilder.buildRouteTree();
    }
    public Object invoke(String route, Object ...args){
        Object result=null;
        try{
            result = routeTree.find(route).getMethod().invoke(null, args);
        }catch (NotFoundException e){
            System.err.println("Route "+ route+ " doesn't exist");
        }catch (NullPointerException e){
            System.err.println(e);
            System.err.println("Method invoked might not be static");
        }catch (Exception e){
            System.err.println(e);
        }
        return result;
    }
    public static Invoker getInstance(){
        return Instance.INSTANCE;
    }
    private static class Instance{
        private static final Invoker INSTANCE = new Invoker();
    }
}
