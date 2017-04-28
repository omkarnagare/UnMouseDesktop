package com.nagare.omkar.unmouse;

import javax.swing.UIManager;
import com.nagare.omkar.unmouse.ui.AppScreen;

public class MainClass {

	public static void main(String[] args) {

		//	    	try {
		//	    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		//	    	        if ("Nimbus".equals(info.getName())) {
		//	    	            UIManager.setLookAndFeel(info.getClassName());
		//	    	            break;
		//	    	        }
		//	    	    }
		//	    	    
		//	    	    AppScreen myApp = new AppScreen();
		//		    	myApp.setUpGUI();
		//	    	    
		//	    	} catch (Exception e) {
		//	    		e.printStackTrace();
		//	    	}


		//	    	try {
		//	            // select Look and Feel
		//	            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		//	            // start application
		//	            AppScreen myApp = new AppScreen();
		//		    	myApp.setUpGUI();
		//	        }
		//	        catch (Exception ex) {
		//	            ex.printStackTrace();
		//	        }


		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // This line gives Windows Theme
			AppScreen myApp = new AppScreen();
			myApp.setUpGUI();

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			//Use default UI
			AppScreen myApp = new AppScreen();
			myApp.setUpGUI();
		}



	}

}
