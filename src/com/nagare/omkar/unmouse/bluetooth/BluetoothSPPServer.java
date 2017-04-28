package com.nagare.omkar.unmouse.bluetooth;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Base64;

import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.imageio.ImageIO;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.JTextArea;

import com.google.gson.JsonSyntaxException;
import com.nagare.omkar.unmouse.entity.PCControlUsingRobot;
import com.nagare.omkar.unmouse.entity.TCPPayload;
import com.nagare.omkar.unmouse.robot.PerformPCFunctions;
import com.nagare.omkar.unmouse.ui.BluetoothModeAppScreen;

public class BluetoothSPPServer {

	private boolean isThreadRunning = false;

	private BluetoothModeAppScreen appFrame = null;

	private JTextArea textArea = null;

	private StreamConnectionNotifier streamConnNotifier = null;

	public BluetoothSPPServer(boolean isThreadRunning, BluetoothModeAppScreen appFrame) {
		super();
		this.isThreadRunning = isThreadRunning;
		this.appFrame = appFrame;

		textArea  = this.appFrame.getStatusTextArea();
	}

	public void startBluetoothSPPServer() throws IOException, JsonSyntaxException, AWTException{

		UUID uuid = new UUID("1101", true);

		//Create the servicve url
		String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";

		//open server url
		streamConnNotifier  = (StreamConnectionNotifier)Connector.open( connectionString );

		//Wait for client connection
		textArea.setText(textArea.getText()+ "\n Waiting for clients to connect...");
//		System.out.println("\nServer Started. Waiting for clients to connect...");
		StreamConnection connection=streamConnNotifier.acceptAndOpen();

		textArea.setText(textArea.getText()+ "\n client bluetooth device connected.");
		RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
//		System.out.println("Remote device address: "+dev.getBluetoothAddress());
//		System.out.println("Remote device name: "+dev.getFriendlyName(true));
		textArea.setText(textArea.getText()+ "\n Remote device address: "+dev.getBluetoothAddress() + "\n Remote device name: "+dev.getFriendlyName(true));
		textArea.setText(textArea.getText()+ "\n connection successful.");


		//initialize streams
		InputStream inStream=connection.openInputStream();
		BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream=connection.openOutputStream();
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(outStream));

		while(true){

			String lineRead=bReader.readLine();
//			System.out.println(lineRead);
			if (lineRead != null){
				PerformPCFunctions.getInstance();
				TCPPayload tcpPayload = PerformPCFunctions.getGsonInstance().fromJson(lineRead, TCPPayload.class);
				handleTCPPayload(tcpPayload);
			}

			String screenCapture = createScreenCaptureString();
			bWriter.write(screenCapture+"\r\n");
			bWriter.write("complete\r\n");
			bWriter.flush();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
	
	public void closeBluetoothConnection() throws IOException{
		
		if(streamConnNotifier != null){
			streamConnNotifier.close();
		}
		
		
	}

	private String createScreenCaptureString() throws AWTException, IOException {

		PerformPCFunctions.getInstance();
		BufferedImage capture = PerformPCFunctions.getRobotInstance().createScreenCapture(PerformPCFunctions.getInstance().getScreenRect());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(capture, "jpg", baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		baos.close();
//		System.out.println(bytes.length);

		return Base64.getMimeEncoder().encodeToString(bytes);
	}

	private void handleTCPPayload(TCPPayload tcpPayload) throws AWTException {

		PCControlUsingRobot.performAction(tcpPayload);

	}	

}
