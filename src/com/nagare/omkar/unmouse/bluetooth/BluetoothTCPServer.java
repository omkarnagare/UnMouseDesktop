package com.nagare.omkar.unmouse.bluetooth;

import com.nagare.omkar.unmouse.ui.BluetoothModeAppScreen;

public class BluetoothTCPServer {
	
	private static Thread tcpServerThread = null;
	
	private static BluetoothTCPRunnable tcpRunnable = null;
	
	public BluetoothTCPServer(BluetoothModeAppScreen appFrame) {
		
		if(tcpServerThread != null){
			
			if(tcpRunnable != null){
				tcpRunnable.terminate();
			}
			tcpRunnable = null;
			
			try {
				tcpServerThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			tcpServerThread = null;
		}
		
		tcpRunnable = setUpServerRunnableCode(appFrame);
		tcpServerThread = new Thread(tcpRunnable);
	
	}
	
	public static void startServer(){
		if( tcpServerThread != null){
			tcpServerThread.start();
		}
	}
	
	public static void stopServer(){
		
		if(tcpRunnable != null){
			tcpRunnable.terminate();
			tcpRunnable = null;
		}
		
		if(tcpServerThread != null){
			
			Thread.currentThread().interrupt();
			
			try {
				tcpServerThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			tcpServerThread = null;
		}
		
	}

	private BluetoothTCPRunnable setUpServerRunnableCode(BluetoothModeAppScreen appFrame) {

		BluetoothTCPRunnable runnable = new BluetoothTCPRunnable();
		runnable.setParentFrame(appFrame);
		runnable.setToRun();		
		return runnable;
		
	}
	

}
