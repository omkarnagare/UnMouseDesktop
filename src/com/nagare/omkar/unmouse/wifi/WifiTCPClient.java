package com.nagare.omkar.unmouse.wifi;

import com.nagare.omkar.unmouse.ui.WifiModeAppScreen;

public class WifiTCPClient {
	
	private static Thread tcpClientThread = null;
	
	private static WifiTCPRunnable tcpRunnable = null;

	public WifiTCPClient(WifiModeAppScreen wifiModeAppScreen) {

		if(tcpClientThread != null){
			
			if(tcpRunnable != null){
				tcpRunnable.terminate();
			}
			tcpRunnable = null;
			
			try {
				tcpClientThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			tcpClientThread = null;
		}
		
		tcpRunnable = setUpClientRunnableCode(wifiModeAppScreen);
		tcpClientThread = new Thread(tcpRunnable);
		
	}

	private WifiTCPRunnable setUpClientRunnableCode(WifiModeAppScreen wifiModeAppScreen) {

		WifiTCPRunnable runnable = new WifiTCPRunnable();
		runnable.setParentFrame(wifiModeAppScreen);
		runnable.setToRun();		
		return runnable;
		
	}

	public void startClient() {

		if( tcpClientThread != null){
			tcpClientThread.start();
		}
		
	}

	public void stopClient() {

		if(tcpRunnable != null){
			tcpRunnable.terminate();
			tcpRunnable = null;
		}
		
		if(tcpClientThread != null){
			
			Thread.currentThread().interrupt();
			
			try {
				tcpClientThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			tcpClientThread = null;
		}
		
	}

}
