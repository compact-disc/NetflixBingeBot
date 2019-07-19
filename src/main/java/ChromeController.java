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

public class ChromeController extends Controller {
	
	private ChromeOptions browserOptions;
	private ChromeDriver chromeDriver;
	
	public ChromeController() {
		
		System.out.println("Chrome Constructor");
		System.setProperty("webdriver.chrome.driver", "drivers/win32/chromedriver.exe");
		browserOptions = new ChromeOptions();
		
		browserOptions.addArguments("start-maximized");
		browserOptions.addArguments("--user-data-dir=" + cookieLocation());
		chromeDriver = new ChromeDriver(browserOptions);
		
		chromeDriver.get("https://www.netflix.com/browse");
		
	}
	
	@Override
	public String cookieLocation() {
		
		String cookieLocation = getUserHome();
		cookieLocation.concat("\\AppData\\Local\\Google\\Chrome\\User Data");
		
		return cookieLocation;
		
	}

	@Override
	public void closeDriver() {
		
		chromeDriver.close();
		
	}

}
