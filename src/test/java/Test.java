/*
 * Christopher DeRoche
 * Date Created: 6/23/2019
 * https://github.com/compact-disc
 * 
 * Main test class used to test various functions in the program, this will contain the main method
 */

package test.java;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test {

	public static void main(String[] args) {
		
		String cookieLocation = System.getProperty("user.home");
		cookieLocation = cookieLocation.concat("\\AppData\\Local\\Google\\Chrome\\User Data");
		
		System.setProperty("webdriver.chrome.driver", "drivers/win32/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("start-maximized");
		options.addArguments("--user-data-dir=" + cookieLocation);
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.get("https://www.netflix.com/browse");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();

	}

}
