/*
 * Christopher DeRoche
 * Date Created: 6/23/2019
 * https://github.com/compact-disc
 * 
 * Controller for Google Chrome
 */

package main.java;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Timer;
import java.util.TimerTask;

public class ChromeController extends Controller {
	
	//Chrome driver and options objects
	private ChromeOptions browserOptions;
	private ChromeDriver chromeDriver;
	
	private Binge binge;
	
	//Default constructor for the chrome controller that will run on start
	public ChromeController() {
		
		//Set the webdriver to the chrome driver located in the drivers directories
		System.setProperty("webdriver.chrome.driver", "drivers/win32/chromedriver.exe");
		
		//Create a new chrome options object to add arguments to the start of the browser
		browserOptions = new ChromeOptions();
		
		//Start the browser maximized
		browserOptions.addArguments("start-maximized");
		//Get the user directory for the default main chrome user and load all of it into the chrome driver
		browserOptions.addArguments("--user-data-dir=" + cookieLocation());
		
		//Start the chrome driver with the argument of the options added into the chrome driver
		chromeDriver = new ChromeDriver(browserOptions);
		
		//Set the driver to go to the browse section of the netflix site
		chromeDriver.get("https://www.netflix.com/browse");
		
	}
	
	//Return the cookie location for the windows user
	@Override
	public String cookieLocation() {
		
		String cookieLocation = getUserHome();
		cookieLocation = cookieLocation.concat("\\AppData\\Local\\Google\\Chrome\\User Data");
		
		return cookieLocation;
		
	}

	//Close the driver for the chrome driver so it does not continue to run after the program is closed
	@Override
	public void closeDriver() {
		
		chromeDriver.close();
		
	}

	//Method to begin the binge for the chrome controller
	@Override
	public void startBinge() {
		
		binge = new Binge();
		
	}
	
	//Method to stop the binging process
	@Override
	public void stopBinge() {
		
		//Check and make sure it is running
		if(binge.getStatus() == 1) {
			
			//Stop the timer
			binge.stop();
			
		}
		
		
	}
	
	class Binge {
		
		//Variables for the timer and the status
		private Timer timer;
		private int bingeStatus = 0;
		
		//Default constructor is called upon starting, thus starting timer and setting status
		public Binge() {
			
			//Create the timer object
			timer = new Timer();
			//Setup the timer schedule, create the inner class to call
			timer.schedule(new CheckForClick(), 500, 500);
			
			//Set the running status of the binge to 1--true
			bingeStatus = 1;
			
		}
		
		//Method to stop the binge process, thus the timer
		public void stop() {
			
			//Cancel the timer
			timer.cancel();
			
			//Set the status to 0--false
			bingeStatus = 0;
			
		}
		
		//Getter for the status of the binge, return the int 0 or 1, true or false
		public int getStatus() {
			
			return bingeStatus;
			
		}
		
		//Inner class for the checking of the video process
		class CheckForClick extends TimerTask{

			//Method called every time the timer is run
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	}

}
