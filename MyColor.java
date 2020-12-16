package PijakoGUI;

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

    public static MyColor grayWith(){
        MyColor grayWith = new MyColor(205,205,205);
        return grayWith;
    }

    public static MyColor grayBlack(){
        MyColor grayBlack = new MyColor(30,30,30);
        return grayBlack;
    }

    public static MyColor white(){
        MyColor white = new MyColor(230,230,230);
        return white;
    }


}
