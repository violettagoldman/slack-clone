package com.invoker.tree;

import javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;

public class RouteTree {
    private RouteItem node;
    private final String routeItemPath;
    private ArrayList<RouteTree> children;

    public RouteTree(RouteItem node, String routeItemPath) {
        this.node = node;
        children = new ArrayList<RouteTree>();
        this.routeItemPath =routeItemPath;
    }

    public ArrayList<RouteTree> getChildren() {
        return children;
    }


    public void insert(RouteItem node){
        _insert(node,0);
    }


    private void _insert(RouteItem node,int depth)throws IllegalArgumentException {
        boolean isLast = (node.getAbsolutePath().length == depth);
        if (isLast) {
            if (this.node == null) {
                this.node = node;
            } else {
                throw new KeyAlreadyExistsException("This path already exists");
            }
            return;
        }
        String routeItemPath = node.getAbsolutePath()[depth];
        for (RouteTree child : this.getChildren()) {
            if (child.routeItemPath.equals(routeItemPath)) {
                child._insert(node, ++depth);
                return;
            }
        }
        RouteTree child = new RouteTree(null, routeItemPath);
        this.children.add(child);
        child._insert(node, ++depth);
    }

    private RouteItem _find(String[] absolutePath,int depth)throws NotFoundException{
        boolean isLast = (absolutePath.length == depth);
        if (isLast) {
                return this.node;
        }
        String routeItemPath = absolutePath[depth];
        for (RouteTree child : this.getChildren()) {
            if (child.routeItemPath.equals(routeItemPath)) {
                return child._find(absolutePath, ++depth);
            }
        }
        throw new NotFoundException("item not found");
    }
    public RouteItem find(String route) throws NotFoundException {
        return _find(route.split("/"),0);
    }



    public String toString() {
        //System.out.println(this.routeItemLeaf +"\nchildren:\n");
        if (children.size() == 0) {
            return "";
        }
        String s = this.routeItemPath;
        for (RouteTree child : children) {
            s += "(" + child.routeItemPath + "), ";
        }
        for (RouteTree child : children) {
            s += "\n" + child.toString();
        }
        return s;
    }
}
