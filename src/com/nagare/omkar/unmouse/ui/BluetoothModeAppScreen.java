package com.nagare.omkar.unmouse.ui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.nagare.omkar.unmouse.bluetooth.BluetoothTCPServer;

public class BluetoothModeAppScreen {

	private JFrame parentFrame = null;

	private JPanel bluetoothDescriptionPanel = null;

	private JLabel btDescriptionHeaderLabel = null;

	private JPanel serverAddressLabelPanel = null;

	private JLabel serverAddressHeaderLabel = null;

	private JLabel serverAddressValueLabel = null;

	private JPanel serverNameLabelPanel = null;

	private JLabel serverNameHeaderLabel = null;

	private JLabel serverNameValueLabel = null;

	private JPanel bluetoothStatusPanel = null;

	private JTextArea statusTextArea = null;

	private JScrollPane statusScroll = null;

	private JPanel closeButtonPanel = null;

	private JButton closeButton = null;

	private BluetoothTCPServer tcpServer = null;

	public BluetoothModeAppScreen(JFrame parentFrame){

		this.parentFrame = parentFrame;
//		this.parentFrame.setTitle("UnMouse- Bluetooth Server");
		this.parentFrame.setLayout(new GridLayout(3, 1));
//		this.parentFrame.setSize(500,400);
		this.parentFrame.setLocationRelativeTo( null );

		//description pannel
		bluetoothDescriptionPanel = new JPanel();
		bluetoothDescriptionPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;

		c.gridx = 0;
		c.gridy = 0;
		btDescriptionHeaderLabel = new JLabel("Unmouse - Bluetooth Server");
		btDescriptionHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		btDescriptionHeaderLabel.setFont(btDescriptionHeaderLabel.getFont().deriveFont(20f));

		bluetoothDescriptionPanel.add(btDescriptionHeaderLabel,c);

		//server address panel
		c.gridx = 0;
		c.gridy = 1;
		serverAddressLabelPanel = new JPanel();
		serverAddressLabelPanel.setLayout(new FlowLayout());
		serverAddressLabelPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		serverAddressHeaderLabel = new JLabel("Bluetooth device address : ");
		serverAddressHeaderLabel.setFont(serverAddressHeaderLabel.getFont().deriveFont(15f));
		serverAddressHeaderLabel.setForeground(Color.GRAY);
		serverAddressValueLabel = new JLabel("bluetooth device address");
		serverAddressValueLabel.setFont(serverAddressValueLabel.getFont().deriveFont(15f));

		serverAddressLabelPanel.add(serverAddressHeaderLabel);
		serverAddressLabelPanel.add(serverAddressValueLabel);

		bluetoothDescriptionPanel.add(serverAddressLabelPanel,c);

		//server name panel
		c.gridx = 0;
		c.gridy = 2;
		serverNameLabelPanel = new JPanel();
		serverNameLabelPanel.setLayout(new FlowLayout());
		serverNameLabelPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		serverNameHeaderLabel = new JLabel("Bluetooth device name : ");
		serverNameHeaderLabel.setFont(serverNameHeaderLabel.getFont().deriveFont(15f));
		serverNameHeaderLabel.setForeground(Color.GRAY);
		serverNameValueLabel = new JLabel("bluetooth device name");
		serverNameValueLabel.setFont(serverNameValueLabel.getFont().deriveFont(15f));

		serverNameLabelPanel.add(serverNameHeaderLabel);
		serverNameLabelPanel.add(serverNameValueLabel);

		bluetoothDescriptionPanel.add(serverNameLabelPanel,c);

		//status panel
		bluetoothStatusPanel = new JPanel();
		bluetoothStatusPanel.setLayout(new GridLayout(1,1));
		bluetoothStatusPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		statusTextArea = new JTextArea();
		statusTextArea.setEditable(false);
		statusTextArea.setText(" Bluetooth server started.");

		statusScroll = new JScrollPane(statusTextArea);
		statusScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		bluetoothStatusPanel.add(statusScroll);

		//close button panel
		closeButtonPanel = new JPanel();
		closeButtonPanel.setLayout(new GridLayout(3, 3));

		closeButton  = new JButton("Close");
		closeButton.setHorizontalAlignment(JButton.CENTER);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				stopBluetoothTCPThread();
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

	public JLabel getServerAddressValueLabel() {
		return serverAddressValueLabel;
	}

	public JLabel getServerNameValueLabel() {
		return serverNameValueLabel;
	}

	public JTextArea getStatusTextArea() {
		return statusTextArea;
	}

	private void stopBluetoothTCPThread() {

		if(tcpServer != null){
			tcpServer.stopServer();
		}
		tcpServer = null;

	}

	public void setUpGUI(){

		parentFrame.add(bluetoothDescriptionPanel);
		parentFrame.add(bluetoothStatusPanel);
		parentFrame.add(closeButtonPanel);
		
		parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		startBluetoothTCPServer();

	}

	private void startBluetoothTCPServer() {

		tcpServer  = new BluetoothTCPServer(this);
		tcpServer.startServer();

	}

	public void hideAllPanels() {

		bluetoothDescriptionPanel.setVisible(false);
		bluetoothStatusPanel.setVisible(false);

		parentFrame.remove(bluetoothDescriptionPanel);
		parentFrame.remove(bluetoothStatusPanel);
		parentFrame.remove(closeButtonPanel);

		showError();

	}

	private void showError() {

		JOptionPane.showMessageDialog(null, "Make sure bluetooth is connected to the PC and is turned on", "Bluetooth NOT DETECTED",JOptionPane.ERROR_MESSAGE);
		System.exit(0);
		
	}

}
