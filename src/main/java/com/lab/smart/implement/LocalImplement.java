package com.lab.smart.implement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.lab.smart.core.EventResult;
import com.lab.smart.core.IImplement;

public class LocalImplement implements IImplement{
    private static Robot robot = null;
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public boolean checkRobot() {
        boolean result = true;
        if (robot == null) {
            result = false;
        }
        return result;
    }
    public EventResult moveMouse(int locationX, int locationY) {
        EventResult result = null;
        if(!checkRobot()) {
            result = EventResult.getEventResult(EventResult.CODE_FAILED, "robot is null");
        } else {
            robot.mouseMove(locationX, locationY);
            result = EventResult.getEventResult();
            System.out.println(locationX + "::::move::::" + locationY);
        }
        return result;
    }
    
    public EventResult leftMouse() {
        return leftMouse(0);
    }
    public EventResult leftMouse(int releaseDelay) {
        EventResult result = null;
        if(!checkRobot()) {
            result = EventResult.getEventResult(EventResult.CODE_FAILED, "robot is null");
        } else {
            pressMouse(InputEvent.BUTTON1_MASK, releaseDelay);
            result = EventResult.getEventResult();
        }
        return result;
    }
    public EventResult rightMouse() {
        return leftMouse(0);
    }
    public EventResult rightMouse(int releaseDelay) {
        EventResult result = null;
        if(!checkRobot()) {
            result = EventResult.getEventResult(EventResult.CODE_FAILED, "robot is null");
        } else {
            pressMouse(InputEvent.BUTTON3_MASK, releaseDelay);
            result = EventResult.getEventResult();
        }
        return result;
    }
    
    public EventResult scrollWheel(int amount) {
    	EventResult result = null;
        if(!checkRobot()) {
            result = EventResult.getEventResult(EventResult.CODE_FAILED, "robot is null");
        } else {
        	robot.mouseWheel(amount);
        	System.out.println("Scroll Wheel :" + amount);
            result = EventResult.getEventResult();
        }
        return result;
	}
    
    private void pressMouse(int mouse, int releaseDelay) {
        robot.mousePress(mouse);
        robot.delay(releaseDelay);
        robot.mouseRelease(mouse);
    }
    private void pressMouse(int mouse) {
        pressMouse(mouse, 0);
    }
    
    private void pressKey(int key, int releaseDelay) {
        robot.keyPress(key);
        robot.delay(releaseDelay);
        robot.keyRelease(key);
    }
    private void pressKey(int key) {
        pressMouse(key, 0);
    }
    
}
