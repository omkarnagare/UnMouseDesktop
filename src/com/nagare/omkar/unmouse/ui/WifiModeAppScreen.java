package com.nagare.omkar.unmouse.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.nagare.omkar.unmouse.wifi.WifiTCPClient;

public class WifiModeAppScreen {

	private JFrame parentFrame = null;
	private JPanel wifiStatusPanel = null;
	private JTextArea statusTextArea = null;
	private JScrollPane statusScroll = null;
	private JPanel closeButtonPanel = null;
	private JButton closeButton = null;
	private JLabel wifiDescriptionHeaderLabel = null;
	
	private WifiTCPClient tcpClient = null;

	public WifiModeAppScreen(JFrame parentFrame) {

		this.parentFrame = parentFrame;
//		this.parentFrame.setTitle("UnMouse Wi-Fi Client");
		this.parentFrame.setLayout(new GridLayout(3, 1));
		this.parentFrame.setLocationRelativeTo( null );

		wifiDescriptionHeaderLabel = new JLabel("UnMouse Wi-Fi Client");
		wifiDescriptionHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		wifiDescriptionHeaderLabel.setFont(wifiDescriptionHeaderLabel.getFont().deriveFont(20f));

		//status panel
		wifiStatusPanel = new JPanel();
		wifiStatusPanel.setLayout(new GridLayout(1,1));
		wifiStatusPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		statusTextArea = new JTextArea();
		statusTextArea.setEditable(false);
		statusTextArea.setText(" Wifi client started.");

		statusScroll = new JScrollPane(statusTextArea);
		statusScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		wifiStatusPanel.add(statusScroll);

		//close button panel
		closeButtonPanel = new JPanel();
		closeButtonPanel.setLayout(new GridLayout(3, 3));

		closeButton  = new JButton("Close");
		closeButton.setHorizontalAlignment(JButton.CENTER);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				stopWifiTCPThread();
				System.exit(0);

			}

		});

		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(closeButton);
		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(new JPanel());
		closeButtonPanel.add(new JPanel());


	}

	public JTextArea getStatusTextArea() {
		return statusTextArea;
	}

	private void stopWifiTCPThread() {

		if(tcpClient != null){
			tcpClient.stopClient();
		}
		tcpClient = null;

	}

	public void setUpGUI() {

		parentFrame.add(wifiDescriptionHeaderLabel);
		parentFrame.add(wifiStatusPanel);
		parentFrame.add(closeButtonPanel);
		parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		startWifiTCPThread();


	}

	private void startWifiTCPThread() {

		tcpClient  = new WifiTCPClient(this);
		tcpClient.startClient();

	}

}
