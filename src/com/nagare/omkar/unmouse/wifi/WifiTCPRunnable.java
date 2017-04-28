package com.nagare.omkar.unmouse.wifi;

import java.io.IOException;

import com.nagare.omkar.unmouse.ui.WifiModeAppScreen;

public class WifiTCPRunnable implements Runnable{
	
	private boolean running = false;

	private WifiModeAppScreen parentFrame = null;

	private WifiTCPCode wifiTCPCode = null;

	public WifiModeAppScreen getParentFrame() {
		return parentFrame;
	}

	public void setToRun() {
		running = true;
	}

	public void setParentFrame(WifiModeAppScreen appFrame) {
		this.parentFrame = appFrame;	
	}

	@Override
	public void run() {

		while(true){

			if(running){
				
				parentFrame.getStatusTextArea().setText(" Wifi client started.");
				
				wifiTCPCode  = new WifiTCPCode(running, parentFrame);
				
				try {
					wifiTCPCode.startwifiTCPConnection();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						wifiTCPCode.closeTCPConnection();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}else{
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}

		}

		
		
	}

	public void terminate() {
		if(wifiTCPCode != null){
			try {
				wifiTCPCode.closeTCPConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		running = false;
		
	}
	
}
