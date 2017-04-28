package com.nagare.omkar.unmouse.robot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import com.google.gson.Gson;

public class PerformPCFunctions {

	private static PerformPCFunctions instance = null;
	
	private static Dimension screenSize = null;

	private static int screenHeight = 0;

	private static int screenWidth = 0;
	
	private static Robot robotInstance = null;
	
	private static Gson gsonInstance = null;
	
	private static Rectangle screenRect = null;
	
	private static Clipboard clipboardInstance = null;

	public PerformPCFunctions() throws AWTException {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		screenRect = new Rectangle(screenSize);

	}

	public static Clipboard getClipboardInstance() {
		
		if (clipboardInstance == null){
			clipboardInstance = Toolkit.getDefaultToolkit().getSystemClipboard();
		}
		return clipboardInstance;
	}

	public static Rectangle getScreenRect() {
		return screenRect;
	}

	public static Dimension getScreenSize() {
		return screenSize;
	}

	public static void setScreenSize(Dimension screenSize) {
		PerformPCFunctions.screenSize = screenSize;
	}

	public static Gson getGsonInstance() {
		if(gsonInstance == null){
			gsonInstance = new Gson();
		}
		return gsonInstance;
	}

	public static void setGsonInstance(Gson gsonInstance) {
		PerformPCFunctions.gsonInstance = gsonInstance;
	}

	public static PerformPCFunctions getInstance() throws AWTException {

		if(instance == null){
			instance = new PerformPCFunctions();
		}		
		return instance;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public static void setScreenHeight(int screenHeight) {
		PerformPCFunctions.screenHeight = screenHeight;
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		PerformPCFunctions.screenWidth = screenWidth;
	}

	public static Robot getRobotInstance() throws AWTException {

		if(robotInstance == null){
			robotInstance = new Robot();
		}		
		return robotInstance;
	}

	public static void setRobotInstance(Robot robotInstance) {
		PerformPCFunctions.robotInstance = robotInstance;
	}



}
