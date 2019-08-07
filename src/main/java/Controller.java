/*
 * Christopher DeRoche
 * Date Created: 6/23/2019
 * https://github.com/compact-disc
 * 
 * Abstract class Controller for creating multiple browser driver support
 */

package main.java;

public abstract class Controller {
	
	//status integer either 0 or 1 for the status of the controller
	private int controllerRunningStatus = 0;
	
	//Abstract methods for the controllers
	public abstract String cookieLocation(); //Method for getting the cookie location for the browser
	public abstract void closeDriver(); //Method to close the driver so it does not keep running
	public abstract void startBinge(); //Method to start the binge
	public abstract void stopBinge(); //Method to stop the binging process
	
	//Constructor for the abstract class of Controller, this will run every time a controller is started
	public Controller() {
		
		this.controllerRunningStatus = 1;
		
	}
	
	//Getter for the status of the controller
	public int getStatus() {
		
		return this.controllerRunningStatus;
		
	}
	
	//Getter for the windows system user home directory
	public String getUserHome() {
		
		return System.getProperty("user.home");
		
	}

}
