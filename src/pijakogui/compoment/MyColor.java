package pijakogui.compoment;

import java.awt.*;

public class MyColor extends Color {
    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }

    public static MyColor blue(){
        MyColor blue = new MyColor(101, 162, 201);
        return blue;
    }

    public static MyColor grayBlue(){
        MyColor grayBlue = new MyColor(35, 44, 49);
        return grayBlue;
    }

    public static MyColor black(){
        MyColor black = new MyColor(50, 50, 50);
        return black;
    }
    public static MyColor gray(){
        MyColor gray = new MyColor(70,70,70);
        return gray;
    }

    public static MyColor grayWithe(){
        MyColor grayWith = new MyColor(180,180,180);
        return grayWith;
    }

    public static MyColor grayBlack(){
        MyColor grayBlack = new MyColor(30,30,30);
        return grayBlack;
    }

    public static MyColor blueAdmin(){
        MyColor blueAdmin = new MyColor(22,131,201);
        return blueAdmin;
    }

    public static MyColor blueUser(){
        MyColor blueUser = new MyColor(102,187,241);
        return blueUser;
    }

    public static MyColor white(){
        MyColor white = new MyColor(230,230,230);
        return white;
    }

    public static MyColor red(){
        MyColor red = new MyColor(186,0,0);
        return red;
    }


}
