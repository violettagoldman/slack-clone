package com.srf.routes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class RouteItem {
    private String[] absolutePath;
    private Method method;
    private String itemPath;

    public RouteItem(String absolutePath, Method method) {
        this.absolutePath = absolutePath.split("/");
        this.itemPath= this.absolutePath[this.absolutePath.length-1];
        this.method = method;
    }

    public String[] getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteItem routeItem = (RouteItem) o;
        return absolutePath.equals(((RouteItem) o).absolutePath);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(method);
        result = 31 * result + Arrays.hashCode(absolutePath);
        return result;
    }
}
