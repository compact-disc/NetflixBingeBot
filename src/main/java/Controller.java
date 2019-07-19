/*
 * Christopher DeRoche
 * Date Created: 6/23/2019
 * https://github.com/compact-disc
 * 
 * Abstract class Controller for creating multiple browser driver support
 */

package main.java;

public abstract class Controller {
	
	private int controllerRunningStatus = 0;
	
	public abstract String cookieLocation();
	public abstract void closeDriver();
	
	public Controller() {
		
		this.controllerRunningStatus = 1;
		
		System.out.println("Controller Constructor");
		
	}
	
	public int getStatus() {
		
		return this.controllerRunningStatus;
		
	}
	
	public String getUserHome() {
		
		return System.getProperty("user.home");
		
	}

}
