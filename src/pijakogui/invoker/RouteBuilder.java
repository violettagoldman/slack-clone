package pijakogui.invoker;

import org.reflections.Reflections;
import pijakogui.invoker.decorators.*;
import pijakogui.invoker.tree.RouteItem;
import pijakogui.invoker.tree.RouteTree;

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
                        new RouteItem(controllerClass.getAnnotation(MainRoute.class).value() + "/" + method.getAnnotation(MethodRoute.class).value(), method)
                );
            }
        }
        return routeItems;
    }

    public static RouteTree buildRouteTree() {
        System.out.println("building route tree...");
        Reflections reflections = new Reflections("pijakogui.services");
        Set<Class<?>> controllers = (reflections.getTypesAnnotatedWith(MainRoute.class));
        RouteTree routeTree = new RouteTree(null, "root");
        for (Class<?> controller : controllers) {
            if (controller.isAnnotationPresent(MainRoute.class)) {
                for (RouteItem routeItem : buildControllerRouteItems(controller)) {
                    routeTree.insert(routeItem);
                }
            }
        }
        System.out.println("done!");

        return routeTree;
    }
}