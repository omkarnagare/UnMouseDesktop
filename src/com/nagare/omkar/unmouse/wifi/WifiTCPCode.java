package com.nagare.omkar.unmouse.wifi;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import com.google.gson.JsonSyntaxException;
import com.nagare.omkar.unmouse.entity.PCControlUsingRobot;
import com.nagare.omkar.unmouse.entity.TCPPayload;
import com.nagare.omkar.unmouse.robot.PerformPCFunctions;
import com.nagare.omkar.unmouse.ui.WifiModeAppScreen;

public class WifiTCPCode {

	private static final String SERVER_IP = "192.168.43.1";
	private static final int SERVER_PORT = 10000;

	private boolean isThreadRunning = false;

	private JTextArea textArea = null;

	private Socket tcpSocket = null;

	public WifiTCPCode(boolean running, WifiModeAppScreen parentFrame) {

		this.isThreadRunning = running;
		textArea  = parentFrame.getStatusTextArea();

	}

	public void closeTCPConnection() throws IOException{

		if(tcpSocket != null){
			tcpSocket.close();
		}

	}

	public void startwifiTCPConnection() throws IOException, JsonSyntaxException, AWTException{

		textArea.setText(textArea.getText()+ "\n looking for server in the network.");
		tcpSocket = new Socket(SERVER_IP , SERVER_PORT);

		// create I/O streams for communication
		DataInputStream is;
		DataOutputStream os;
		is = new DataInputStream(tcpSocket.getInputStream());
		os = new DataOutputStream(tcpSocket.getOutputStream());
		
		textArea.setText(textArea.getText()+ "\n connected to server.");

		while(true){
			
			String lineRead = is.readLine();
//			System.out.println(lineRead);
			if (lineRead != null){
				PerformPCFunctions.getInstance();
				TCPPayload tcpPayload = PerformPCFunctions.getGsonInstance().fromJson(lineRead, TCPPayload.class);
				handleTCPPayload(tcpPayload);
			}
			
			String screenCapture = createScreenCaptureString();
			os.write((screenCapture+"\n").getBytes());
			os.write(("complete\n").getBytes());

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
