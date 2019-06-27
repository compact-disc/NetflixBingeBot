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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ApplicationWindow {

	private static final int CENTER = 0;
	private JFrame frmNetflixBingeBuddy;
	private JLabel step1Label, step2Label, beginShowLabel, step3Label, titleLabel, statusCurrent, statusLabel, browserInstruction, step4Label;
	private JButton launchBrowerBtn, startBingeBtn, quitBtn, helpBtn, stopBingeBtn;
	private GroupLayout groupLayout;
	private JComboBox browserSelector;

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
		
		frmNetflixBingeBuddy = new JFrame();
		frmNetflixBingeBuddy.setTitle("Netflix Binge Buddy");
		frmNetflixBingeBuddy.setResizable(false);
		frmNetflixBingeBuddy.setBounds(100, 100, 375, 325);
		frmNetflixBingeBuddy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		step1Label = new JLabel("Step 1:");
		step2Label = new JLabel("Step 2:");
		step3Label = new JLabel("Step 3:");
		step4Label = new JLabel("Step 4:");
		beginShowLabel = new JLabel("Begin your show");
		browserInstruction = new JLabel("Choose Web Browser");
		
		titleLabel = new JLabel("<html>\r\n<center>\r\n<h2>Netflix Binge Buddy</h2>\r\n<b>Christopher DeRoche</b>\r\n</center>\r\n</html>", CENTER);
		titleLabel.setBackground(Color.LIGHT_GRAY);
		titleLabel.setOpaque(true);
		
		statusLabel = new JLabel("Status:");
		statusLabel.setOpaque(true);
		
		statusCurrent = new JLabel("Not running");
		statusCurrent.setOpaque(true);
		statusCurrent.setForeground(Color.BLACK);
		statusCurrent.setBackground(Color.RED);
		
		launchBrowerBtn = new JButton("Launch Browser");
		launchBrowerBtn.setBackground(Color.WHITE);

		startBingeBtn = new JButton("Start Binge");
		startBingeBtn.setBackground(Color.GREEN);
		
		quitBtn = new JButton("Quit");
		quitBtn.setBackground(Color.WHITE);
		
		helpBtn = new JButton("Help");
		helpBtn.setBackground(Color.WHITE);

		stopBingeBtn = new JButton("Stop Binge");
		stopBingeBtn.setBackground(Color.RED);
		
		browserSelector = new JComboBox();
		browserSelector.setBackground(Color.WHITE);
		browserSelector.setModel(new DefaultComboBoxModel(new String[] {"Chrome", "Firefox"}));
		browserSelector.setOpaque(true);

		groupLayout = new GroupLayout(frmNetflixBingeBuddy.getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(step3Label)
							.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
							.addComponent(beginShowLabel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(step4Label)
								.addComponent(statusLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(statusCurrent)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(startBingeBtn, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addGap(29)
									.addComponent(stopBingeBtn, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))))
						.addComponent(helpBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
						.addComponent(quitBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(step2Label)
								.addComponent(step1Label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(browserInstruction)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(browserSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(launchBrowerBtn, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(step1Label)
						.addComponent(browserInstruction)
						.addComponent(browserSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(step2Label)
						.addComponent(launchBrowerBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(step3Label)
						.addComponent(beginShowLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(step4Label)
						.addComponent(startBingeBtn)
						.addComponent(stopBingeBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(statusLabel)
						.addComponent(statusCurrent))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(helpBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quitBtn)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		frmNetflixBingeBuddy.getContentPane().setLayout(groupLayout);
		
	}
}
