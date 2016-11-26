package com.lab.smart.core;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import com.lab.smart.implement.LocalImplement;

public class Client {

    private int locationX = 0;
    private int locationY = 0;
    private int screenWidth = 0;
    private int screenHeight = 0;
    {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        locationX = mousePoint.x;
        locationY = mousePoint.y;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = dim.width;
        screenHeight = dim.height;
    }
    private static Client c = new Client();
    IImplement implement = new LocalImplement();

    public static void move(int x, int y) {
        System.out.println(x +"mouseIn" +y);
        c.locationX = c.locationX + (int)(x*2.5);
        c.locationY = c.locationY + (int)(y*2.5);
        if (c.locationX < 0) {
            c.locationX = 0;
        } else if (c.locationX > c.screenWidth) {
            c.locationX = c.screenWidth;
        }
        if (c.locationY < 0) {
            c.locationY = 0;
        } else if (c.locationY > c.screenHeight) {
            c.locationY = c.screenHeight;
        }
        
        c.implement.moveMouse(c.locationX, c.locationY);
    }
    public static void leftMouse() {
        c.implement.leftMouse(0);
    }
    
    public static void rightMouse() {
    	c.implement.rightMouse(0);
    }
}
