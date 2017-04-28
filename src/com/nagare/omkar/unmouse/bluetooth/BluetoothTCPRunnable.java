package com.nagare.omkar.unmouse.bluetooth;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import com.nagare.omkar.unmouse.ui.BluetoothModeAppScreen;

public class BluetoothTCPRunnable implements Runnable{

	private boolean running = false;

	private BluetoothModeAppScreen parentFrame = null;

	private BluetoothSPPServer bluetoothSPPServer = null;

	public BluetoothModeAppScreen getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(BluetoothModeAppScreen appFrame) {
		this.parentFrame = appFrame;
	}

	public void setToRun(){
		running = true;
	}

	public void terminate(){
		if(bluetoothSPPServer != null){
			try {
				bluetoothSPPServer.closeBluetoothConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		running = false;
	}

	@Override
	public void run() {

		LocalDevice localDevice;
		try {
			localDevice = LocalDevice.getLocalDevice();

//			System.out.println("Address: "+localDevice.getBluetoothAddress());
//			System.out.println("Name: "+localDevice.getFriendlyName());

			parentFrame.getServerAddressValueLabel().setText(localDevice.getBluetoothAddress());
			parentFrame.getServerNameValueLabel().setText(localDevice.getFriendlyName());

			while(true){

				if(running){
					
					parentFrame.getStatusTextArea().setText(" Server Started.");
					
					bluetoothSPPServer  = new BluetoothSPPServer(running, parentFrame);
					try {
						bluetoothSPPServer.startBluetoothSPPServer();
					} catch (Exception e) {
						try {
							bluetoothSPPServer.closeBluetoothConnection();
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


		} catch (BluetoothStateException e) {

			e.printStackTrace();
			parentFrame.hideAllPanels();

		}

	}

}
