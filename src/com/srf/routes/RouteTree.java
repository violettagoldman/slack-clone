package com.srf.routes;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;

public class RouteTree {
    private RouteItem node;
    private String routeItemPath;
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

    //depth doit être appleé à partir de 1;

    private void _insert(RouteItem node,int depth)throws IllegalArgumentException {
        if (depth < 0) {
            throw new IllegalArgumentException("Depth must be >=0");
        }
        boolean isLast = (node.getAbsolutePath().length == depth);
        if (isLast) {
            if (this.node == null) {
                this.node = node;
            } else {
                throw new KeyAlreadyExistsException("This path already exists");
            }
            return;
        }
        String nodeRouteItemLeaf = node.getAbsolutePath()[depth];
        for (RouteTree child : this.getChildren()) {
            if (child.routeItemPath.equals(nodeRouteItemLeaf)) {
                child._insert(node, ++depth);
                return;
            }
        }
        RouteTree child = new RouteTree(null, nodeRouteItemLeaf);
        this.children.add(child);
        child._insert(node, ++depth);
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
