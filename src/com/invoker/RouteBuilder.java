package com.invoker;

import com.controllers.Controller;
import com.invoker.decorators.*;
import com.invoker.tree.RouteItem;
import com.invoker.tree.RouteTree;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class RouteBuilder {
    private RouteBuilder() {
    }

    private static boolean isRoutingAnnotationPresent(Method method){
        return method.isAnnotationPresent(Get.class) ||
                method.isAnnotationPresent(Post.class) ||
                method.isAnnotationPresent(Delete.class) ||
                method.isAnnotationPresent(Put.class);
    }

    private static ArrayList<RouteItem> buildControllerRouteItems(Class<?> controllerClass) {
        Method[] methods = controllerClass.getMethods();
        ArrayList<RouteItem> routeItems = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodRoute.class)) {
                routeItems.add(
                        new RouteItem(controllerClass.getAnnotation(ControllerRoute.class).value() + "/" + method.getAnnotation(MethodRoute.class).value(), method)
                );
            }
        }
        return routeItems;
    }

    public static RouteTree buildRouteTree() {
        System.out.println("building route tree...");
        Reflections reflections = new Reflections("com.controllers");
        Set<Class<?>> controllers = (reflections.getTypesAnnotatedWith(ControllerRoute.class));
        RouteTree routeTree = new RouteTree(null, "GET");
        for (Class<?> controller : controllers) {
            if (controller.isAnnotationPresent(ControllerRoute.class)) {
                for (RouteItem routeItem : buildControllerRouteItems(controller)) {
                    routeTree.insert(routeItem);
                }
            }
        }
        System.out.println("done!");

        return routeTree;
    }
}