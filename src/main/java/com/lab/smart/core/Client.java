package com.lab.smart.core;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import com.lab.smart.implement.LocalImplement;

public class Client {
	
	private static final double ZOOM_SIZE = 3;

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
        System.out.println(x + "mouseIn" + y);
        c.locationX = c.locationX + (int)(x * ZOOM_SIZE);
        c.locationY = c.locationY + (int)(y * ZOOM_SIZE);
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
    
    public static void scroll(int xa, int xb) {
    	int amount = 0;
    	if (xa * xb > 0) {
    		if (xa > 0) {
    			amount = Math.max(xa, xb);
    		} else {
    			amount = Math.min(xa, xb);
    		}
    	}
    	amount /= ZOOM_SIZE;
    	c.implement.scrollWheel(-amount);
    }
}
