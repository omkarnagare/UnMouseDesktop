package com.nagare.omkar.unmouse.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AppScreen {

	private JFrame appFrame = null;

	private JRadioButton wifiButton = null;

	private JRadioButton bluetoothButton = null;

	private JButton confirmButton = null;

	private ButtonGroup modeSelectionGroup = null;

	private JLabel welcomeLabel = null;

	private JLabel appDescriptionLabel = null;

	private JPanel radioButtonPanel = null;

	private JPanel confirmButtonPanel = null;

	private JPanel appDescriptionPanel = null;

	public AppScreen(){

		initializeComponents();

	}


	private void initializeComponents() {

		appFrame = new JFrame("UnMouse-Desktop");//creating instance of JFrame 

		setUpModeSelectionPanel();

	}

	private void setUpModeSelectionPanel() {

		//app description panel
		appDescriptionPanel = new JPanel();
		appDescriptionPanel.setLayout(new BoxLayout(appDescriptionPanel, BoxLayout.Y_AXIS));

		welcomeLabel = new JLabel("Welcome to Un-mouse desktop app!!");
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(20f)); 

		appDescriptionLabel = new JLabel("Please select the mode and click \"Start\"");
		appDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appDescriptionLabel.setFont(appDescriptionLabel.getFont().deriveFont(15f)); 

		appDescriptionPanel.add(Box.createVerticalStrut(20));
		appDescriptionPanel.add(welcomeLabel);
		appDescriptionPanel.add(appDescriptionLabel);

		//radio panel
		radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new GridLayout(1, 2));

		wifiButton =new JRadioButton("Wi-Fi");//creating instance of JButton
		wifiButton.setHorizontalAlignment(JButton.CENTER);
		bluetoothButton = new JRadioButton("Bluetooth");
		bluetoothButton.setHorizontalAlignment(JButton.CENTER);

		modeSelectionGroup = new ButtonGroup();
		modeSelectionGroup.add(bluetoothButton);
		modeSelectionGroup.add(wifiButton);

		radioButtonPanel.add(wifiButton);
		radioButtonPanel.add(bluetoothButton);

		//confirm button pannel
		confirmButtonPanel = new JPanel();
		confirmButtonPanel.setLayout(new GridLayout(3,3));

		confirmButton = new JButton("Start");
		confirmButton.setHorizontalAlignment(JButton.CENTER);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				startSelectedModeScreen();

			}

		});
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(confirmButton);
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(new JPanel());
		confirmButtonPanel.add(new JPanel());

	}


	private void startSelectedModeScreen() {

		if(!wifiButton.isSelected() && !bluetoothButton.isSelected()){

			JOptionPane.showMessageDialog(appFrame, "select one of the mode first", "Warning",JOptionPane.WARNING_MESSAGE);

		}else{

			if(wifiButton.isSelected()){
				//start wifi thread
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to continue with Wi-Fi?", "Confirm mode",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					hideModeSelectionItems(true);

				} else {
					JOptionPane.showMessageDialog(appFrame, "You have to select one of the mode to continue!!", "Warning",JOptionPane.WARNING_MESSAGE);
				}

			}

			if(bluetoothButton.isSelected()){
				//start bluetooth
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to continue with Bluetooth?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					hideModeSelectionItems(false);

				} else {
					JOptionPane.showMessageDialog(appFrame, "You have to select one of the mode to continue!!", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			}

		}

	}


	private void hideModeSelectionItems(boolean isWifiModeSelected) {

		appDescriptionPanel.setVisible(false);
		radioButtonPanel.setVisible(false);
		confirmButtonPanel.setVisible(false);

		appFrame.remove(appDescriptionPanel);
		appFrame.remove(radioButtonPanel);
		appFrame.remove(confirmButtonPanel);
		addNewComponentsBasedOnMode(isWifiModeSelected);

	}


	private void addNewComponentsBasedOnMode(boolean isWifiModeSelected) {

		if(isWifiModeSelected){
			
			WifiModeAppScreen wifiScreen = new WifiModeAppScreen(appFrame);
			wifiScreen.setUpGUI();
			
		}else{
			
			BluetoothModeAppScreen btScreen = new BluetoothModeAppScreen(appFrame);
			btScreen.setUpGUI();
			
		}

	}


	public void setUpGUI(){

		appFrame.add(appDescriptionPanel);
		appFrame.add(radioButtonPanel);
		appFrame.add(confirmButtonPanel);

		appFrame.setSize(500,400);//500 width and 400 height  
		appFrame.setLayout(new GridLayout(3, 1));//using no layout managers  
		appFrame.setVisible(true);//making the frame visible
		appFrame.setLocationRelativeTo( null );
		final List<Image> icons = new ArrayList<Image>();
		try {
			icons.add(ImageIO.read(new File("unmouse_icon.png")));
			icons.add(ImageIO.read(new File("unmouse_icon-1.png")));
			icons.add(ImageIO.read(new File("unmouse_icon-2.png")));
			icons.add(ImageIO.read(new File("unmouse_icon-3.png")));
			icons.add(ImageIO.read(new File("unmouse_icon-4.png")));
			icons.add(ImageIO.read(new File("unmouse_icon-5.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appFrame.setIconImages(icons);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    

	}

}
