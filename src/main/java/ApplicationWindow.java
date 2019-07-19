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

	private JFrame frmNetflixBingeBuddy;
	private JLabel beginShowLabel, titleLabel, statusCurrent, statusLabel, browserInstruction;
	private JButton launchBrowerBtn, startBingeBtn, quitBtn, helpBtn, stopBingeBtn, closeBrowserBtn;
	private JComboBox browserSelector;
	private JTextPane txtpnProfileName;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbc_titleLabel, gbc_browserInstruction, gbc_browserSelector, gbc_txtpnProfileName,
	gbc_launchBrowerBtn, gbc_beginShowLabel, gbc_startBingeBtn, gbc_stopBingeBtn, gbc_statusLabel, gbc_statusCurrent,
	gbc_helpBtn, gbc_quitBtn, gbc_closeBrowserBtn;
	
	private FlashingColors flashingColors;
	
	private Controller browserController;
	
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
		
		flashingColors = new FlashingColors();
		
		frmNetflixBingeBuddy = new JFrame();
		frmNetflixBingeBuddy.setTitle("Netflix Binge Buddy");
		frmNetflixBingeBuddy.setResizable(false);
		frmNetflixBingeBuddy.setBounds(100, 100, 365, 410);
		frmNetflixBingeBuddy.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
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
		 * Start binge button action event
		*/
		startBingeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(flashingColors.getStatus() == 0) {
					flashingColors.start();
				}
				
			}
		});
		
		/*
		 * Stop binge button action event
		*/
		stopBingeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(flashingColors.getStatus() == 1) {
					flashingColors.stop();
				}
				
			}
		});
		
		/*
		 * launch browser button action event
		*/
		launchBrowerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				
				browserLaunched = 0;
				browserController.closeDriver();
				
			}
		});

		/*
		 * click event for the profile text entry box
		*/
		txtpnProfileName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				if(txtpnProfileName.getText().equals("Profile Name"))
					txtpnProfileName.setText("");
				
			}
		});
	
		/*
		 * help button action event
		*/
		helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				
				if(browserLaunched == 1) {
					
					browserController.closeDriver();
				}
				
				System.exit(0);
				
			}
		});
		
		/*
		 * Close window custom close operation to make sure that the driver closes
		 */
		frmNetflixBingeBuddy.addWindowListener(new WindowAdapter()  {
			public void windowClosing(WindowEvent e){
				
				if(browserLaunched == 1) {
					
					browserController.closeDriver();
				}
				
				System.exit(0);
				
			}
			
		});
		
	}
	
	class FlashingColors {
		
		private Timer timer;
		private int currentColor = 0;
		private int status = 0;
		
		public int getStatus() {
			return status;
		}
		
		public void start() {
			
			this.timer = new Timer();
			timer.schedule(new TimerCall(), 500, 500);
			statusCurrent.setText("Running");
			statusCurrent.setBackground(Color.GREEN);
			status = 1;
			
		}
		
		public void stop(){
			
			timer.cancel();
			statusCurrent.setText("Not Running");
			statusCurrent.setBackground(Color.RED);
			status = 0;
			
		}
		
		public void flashColor() {
			
			if(currentColor == 0) {
				statusCurrent.setBackground(Color.RED);
				currentColor = 1;
			}else if(currentColor == 1) {
				statusCurrent.setBackground(Color.GREEN);
				currentColor = 0;
			}
			
		}
		
		class TimerCall extends TimerTask{

			@Override
			public void run() {
				
				flashColor();
				
			}
			
		}
		
	}

}
