/*
 * Christopher DeRoche
 * Date Created: 6/23/2019
 * https://github.com/compact-disc
 * 
 * Main located here, the window for the program is also here
 */

package main.java;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Desktop;

public class ApplicationWindow {

	//All the variables for the window
	private JFrame frmNetflixBingeBuddy;
	private JLabel beginShowLabel, titleLabel, statusCurrent, statusLabel, browserInstruction;
	private JButton launchBrowerBtn, startBingeBtn, quitBtn, helpBtn, stopBingeBtn, closeBrowserBtn;
	private JComboBox browserSelector;
	private JTextPane txtpnProfileName;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbc_titleLabel, gbc_browserInstruction, gbc_browserSelector, gbc_txtpnProfileName,
	gbc_launchBrowerBtn, gbc_beginShowLabel, gbc_startBingeBtn, gbc_stopBingeBtn, gbc_statusLabel, gbc_statusCurrent,
	gbc_helpBtn, gbc_quitBtn, gbc_closeBrowserBtn;
	
	//Used for collecting the profile name
	private String profileName;
	
	//Object for the flashing status window
	private FlashingColors flashingColors;
	
	//Object declaration for the controller to be launched
	private Controller browserController;
	
	//variable used to determine if the browser is currently running or not
	//This is used to make sure that the program does not break while closing or trying to open multiple browsers
	private int browserLaunched = 0;

	//Launch the application
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					ApplicationWindow window = new ApplicationWindow();
					window.frmNetflixBingeBuddy.setVisible(true);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
	}

	//Create the application
	public ApplicationWindow() {
		
		initialize();
		
	}

	//Initialize the contents of the frame
	private void initialize() {
		
		//Create a new object of the flashing colors inner class to allow the status to flash
		flashingColors = new FlashingColors();
		
		//Create the JFrame with parameters
		frmNetflixBingeBuddy = new JFrame();
		frmNetflixBingeBuddy.setResizable(false);
		frmNetflixBingeBuddy.setTitle("Netflix Binge Buddy");
		frmNetflixBingeBuddy.setBounds(100, 100, 365, 410);
		
		//Set the default close operation to do nothing--this is so a custom close operation can be created later
		frmNetflixBingeBuddy.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/*
		 * Begin all of the window component creation and formatting
		 */
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {35, 35, 35, 35, 0, 35};
		gridBagLayout.rowHeights = new int[] {35, 35, 35, 35, 35};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		frmNetflixBingeBuddy.getContentPane().setLayout(gridBagLayout);
		
		titleLabel = new JLabel("<html>\r\n<center>\r\n<h2>Netflix Binge Buddy</h2>\r\n<b>Christopher DeRoche</b>\r\n</center>\r\n</html>", 0);
		titleLabel.setBackground(Color.LIGHT_GRAY);
		titleLabel.setOpaque(true);
		gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.fill = GridBagConstraints.BOTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridwidth = 7;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		frmNetflixBingeBuddy.getContentPane().add(titleLabel, gbc_titleLabel);
		
		browserInstruction = new JLabel("Choose Web Browser");
		gbc_browserInstruction = new GridBagConstraints();
		gbc_browserInstruction.fill = GridBagConstraints.HORIZONTAL;
		gbc_browserInstruction.insets = new Insets(0, 0, 5, 5);
		gbc_browserInstruction.gridwidth = 3;
		gbc_browserInstruction.gridx = 0;
		gbc_browserInstruction.gridy = 1;
		frmNetflixBingeBuddy.getContentPane().add(browserInstruction, gbc_browserInstruction);
		
		browserSelector = new JComboBox();
		browserSelector.setBackground(Color.WHITE);
		browserSelector.setModel(new DefaultComboBoxModel(new String[] {"Chrome", "Firefox"}));
		browserSelector.setOpaque(true);
		gbc_browserSelector = new GridBagConstraints();
		gbc_browserSelector.insets = new Insets(0, 0, 5, 0);
		gbc_browserSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_browserSelector.gridwidth = 4;
		gbc_browserSelector.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_browserSelector.gridx = 3;
		gbc_browserSelector.gridy = 1;
		frmNetflixBingeBuddy.getContentPane().add(browserSelector, gbc_browserSelector);
						
		txtpnProfileName = new JTextPane();
		txtpnProfileName.setText("Profile Name");
		gbc_txtpnProfileName = new GridBagConstraints();
		gbc_txtpnProfileName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnProfileName.gridwidth = 7;
		gbc_txtpnProfileName.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_txtpnProfileName.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnProfileName.gridx = 0;
		gbc_txtpnProfileName.gridy = 2;
		frmNetflixBingeBuddy.getContentPane().add(txtpnProfileName, gbc_txtpnProfileName);
						
		launchBrowerBtn = new JButton("Launch Browser");
		launchBrowerBtn.setBackground(Color.GREEN);
		gbc_launchBrowerBtn = new GridBagConstraints();
		gbc_launchBrowerBtn.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_launchBrowerBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_launchBrowerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_launchBrowerBtn.gridwidth = 3;
		gbc_launchBrowerBtn.gridx = 0;
		gbc_launchBrowerBtn.gridy = 3;
		frmNetflixBingeBuddy.getContentPane().add(launchBrowerBtn, gbc_launchBrowerBtn);
		
		closeBrowserBtn = new JButton("Close Browser");
		closeBrowserBtn.setBackground(Color.RED);
		gbc_closeBrowserBtn = new GridBagConstraints();
		gbc_closeBrowserBtn.gridwidth = 4;
		gbc_closeBrowserBtn.insets = new Insets(0, 0, 5, 5);
		gbc_closeBrowserBtn.gridx = 3;
		gbc_closeBrowserBtn.gridy = 3;
		frmNetflixBingeBuddy.getContentPane().add(closeBrowserBtn, gbc_closeBrowserBtn);

		beginShowLabel = new JLabel("Begin your show");
		gbc_beginShowLabel = new GridBagConstraints();
		gbc_beginShowLabel.insets = new Insets(0, 0, 5, 0);
		gbc_beginShowLabel.gridwidth = 7;
		gbc_beginShowLabel.gridx = 0;
		gbc_beginShowLabel.gridy = 4;
		frmNetflixBingeBuddy.getContentPane().add(beginShowLabel, gbc_beginShowLabel);
						
		startBingeBtn = new JButton("Start Binge");
		startBingeBtn.setBackground(Color.GREEN);
		gbc_startBingeBtn = new GridBagConstraints();
		gbc_startBingeBtn.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_startBingeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_startBingeBtn.insets = new Insets(0, 0, 5, 5);
		gbc_startBingeBtn.gridwidth = 3;
		gbc_startBingeBtn.gridx = 0;
		gbc_startBingeBtn.gridy = 5;
		frmNetflixBingeBuddy.getContentPane().add(startBingeBtn, gbc_startBingeBtn);
				
		stopBingeBtn = new JButton("Stop Binge");
		stopBingeBtn.setBackground(Color.RED);
		gbc_stopBingeBtn = new GridBagConstraints();
		gbc_stopBingeBtn.gridwidth = 4;
		gbc_stopBingeBtn.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_stopBingeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_stopBingeBtn.insets = new Insets(0, 0, 5, 0);
		gbc_stopBingeBtn.gridx = 3;
		gbc_stopBingeBtn.gridy = 5;
		frmNetflixBingeBuddy.getContentPane().add(stopBingeBtn, gbc_stopBingeBtn);
				
		statusLabel = new JLabel("Status:");
		statusLabel.setOpaque(true);
		gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.insets = new Insets(0, 0, 5, 5);
		gbc_statusLabel.gridx = 0;
		gbc_statusLabel.gridy = 6;
		frmNetflixBingeBuddy.getContentPane().add(statusLabel, gbc_statusLabel);
		
		statusCurrent = new JLabel("Not running");
		statusCurrent.setOpaque(true);
		statusCurrent.setForeground(Color.BLACK);
		statusCurrent.setBackground(Color.RED);
		gbc_statusCurrent = new GridBagConstraints();
		gbc_statusCurrent.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_statusCurrent.insets = new Insets(0, 0, 5, 5);
		gbc_statusCurrent.gridwidth = 2;
		gbc_statusCurrent.gridx = 1;
		gbc_statusCurrent.gridy = 6;
		frmNetflixBingeBuddy.getContentPane().add(statusCurrent, gbc_statusCurrent);
		
		helpBtn = new JButton("Help");
		helpBtn.setBackground(Color.WHITE);
		gbc_helpBtn = new GridBagConstraints();
		gbc_helpBtn.anchor = GridBagConstraints.NORTH;
		gbc_helpBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_helpBtn.insets = new Insets(0, 0, 5, 0);
		gbc_helpBtn.gridwidth = 7;
		gbc_helpBtn.gridx = 0;
		gbc_helpBtn.gridy = 7;
		frmNetflixBingeBuddy.getContentPane().add(helpBtn, gbc_helpBtn);
		
		quitBtn = new JButton("Quit");
		quitBtn.setBackground(Color.WHITE);
		gbc_quitBtn = new GridBagConstraints();
		gbc_quitBtn.anchor = GridBagConstraints.NORTH;
		gbc_quitBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_quitBtn.gridwidth = 7;
		gbc_quitBtn.gridx = 0;
		gbc_quitBtn.gridy = 8;
		frmNetflixBingeBuddy.getContentPane().add(quitBtn, gbc_quitBtn);
		/*
		 * End all of the window component building and formatting
		 */
		
		/*
		 * Start binge button action event
		*/
		startBingeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//The colors are only allowed to be launched if the status is already 0 and if the browser is launched
				if(flashingColors.getStatus() == 0 && browserLaunched == 1) {
					
					//Start the flashing colors
					flashingColors.start();
					
					//Start the binge for any browser
					browserController.startBinge();
					
				}
				
			}
		});
		
		/*
		 * Stop binge button action event
		*/
		stopBingeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If the colors are flashing and the browser is launched then the flashing colors can be stopped
				if(flashingColors.getStatus() == 1 && browserLaunched == 1) {
					flashingColors.stop();
				}
				
			}
		});
		
		/*
		 * launch browser button action event
		*/
		launchBrowerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If the profile name text field is not "Profile Name" and is not empty, and there is not another browser launched
				//Then the browser is allowed to be launched
				//Depending on which browser is chosen, the object of that browsers driver will be created and status = 1
				if(!txtpnProfileName.getText().equals("Profile Name") && !txtpnProfileName.getText().isEmpty() && browserLaunched == 0) {
				
					if(browserSelector.getSelectedIndex() == 0) {
					
						browserLaunched = 1;
						browserController = new ChromeController();

					
					}else if(browserSelector.getSelectedIndex() == 1) {
					
						browserLaunched = 1;
						browserController = new FirefoxController();
					
					}
				
				}
				
			}
		});
		
		/*
		 * close browser button action event
		*/
		closeBrowserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If a browser is launched and the button is clicked then the browser will close
				if(browserLaunched == 1) {
				
					browserLaunched = 0;
					browserController.closeDriver();
				
				}
				
			}
		});

		/*
		 * click event for the profile text entry box
		*/
		txtpnProfileName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				//When user initially clicks on the profile name text field it will clear automatically
				if(txtpnProfileName.getText().equals("Profile Name")) {
					txtpnProfileName.setText("");
				}
				
			}
		});
	
		/*
		 * help button action event
		*/
		helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Try-catch for opening the browser for the help section of the website, link to github page
				//Driver not needed, this will just use the default browser of the system
				try {
					Desktop.getDesktop().browse(new URL("https://github.com/compact-disc/netflix-binge-buddy").toURI());
				} catch (IOException | URISyntaxException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
		});
		
		/*
		 * quit button action event
		*/
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//Try-catch to account for browser launch issue.
				//It will still close the program even if there is no browser launched if it catches
				//But will still close normally if it it does not catch
				try {
				
					//Check if the browser is launched, then close it if it is
					if(browserLaunched == 1) {
					
						browserController.closeDriver();
					}
				
				}catch (Exception ex) {
					
					//Print error, close program
					
					ex.printStackTrace();
					System.exit(0);
					
				}
				
				//shutdown the java program
				System.exit(0);
				
			}
		});
		
		/*
		 * Close window custom close operation to make sure that the driver closes
		 */
		frmNetflixBingeBuddy.addWindowListener(new WindowAdapter()  {
			public void windowClosing(WindowEvent e){
				
				//Try-catch to account for browser launch issue.
				//It will still close the program even if there is no browser launched if it catches
				//But will still close normally if it it does not catch
				try {
				
					//If the browser is opened, then close it first before shutting down the entire program
					if(browserLaunched == 1) {
					
						browserController.closeDriver();
					
					}
				
				}catch (Exception ex) {
					
					//Print error, close program
					ex.printStackTrace();
					System.exit(0);
					
				}
				
				//Shutdown the main java program
				System.exit(0);
				
			}
			
		});
		
	}
	
	/*
	 * Class to control the flashing color of the status in the program window
	 */
	class FlashingColors {
		
		//Create a timer
		private Timer timer;
		
		//Create status for the running and current color of the flashing colors
		private int currentColor = 0;
		private int status = 0;
		
		//Getter for the status of the flashing colors
		public int getStatus() {
			return status;
		}
		
		//Start the flashing colors
		public void start() {
			
			//Create the timer object
			this.timer = new Timer();
			//Set the timer schedule for the flashing colors and then what to call
			timer.schedule(new TimerCall(), 500, 500);
			
			//Set the text in the text field to RUNNING
			statusCurrent.setText("Running");
			//set the text field to background green
			statusCurrent.setBackground(Color.GREEN);
			
			//set the status to 1--which is running, as in true
			status = 1;
			
		}
		
		//Method to stop the flashing colors
		public void stop(){
			
			//Stop the timer
			timer.cancel();
			
			//Set the text to not running
			statusCurrent.setText("Not Running");
			//Set color to red
			statusCurrent.setBackground(Color.RED);
			//set the status to 0--which is false
			status = 0;
			
		}
		
		//Method that will change the color with each interation
		public void flashColor() {
			
			//Simple if-else-if to check which color is displayed, then change it to the opposite of it
			if(currentColor == 0) {
				statusCurrent.setBackground(Color.RED);
				currentColor = 1;
			}else if(currentColor == 1) {
				statusCurrent.setBackground(Color.GREEN);
				currentColor = 0;
			}
			
		}
		
		//Inner class that is created for the timer to keep calling in the flashing colors inner class
		class TimerCall extends TimerTask{

			//Run method to be called whenever the timer hits another iteration
			@Override
			public void run() {
				
				//Call the flash color method every time the timer is called
				flashColor();
				
			}
			
		}
		
	}

}
