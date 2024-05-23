
package com.project.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.google.common.io.Files;

public class Base {
	public static WebDriver driver;
	public static Properties prop;

	// To call different browsers
	public void invokeBrowser() {
		prop = new Properties();
		// To Open Chrome Browser
		try {
			prop.load(new FileInputStream("src/main/java/Config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String browser = (System.getProperty("browser") == null) ? prop.getProperty("browser"):System.getProperty("browser");
		System.out.println("========================================================");
		System.out.println("Opening " + browser + " Browser");
		System.out.println("========================================================");

		
		if(browser.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1536,864));
			}
			else
			{
				driver = new ChromeDriver();
			}
			
			// Set the path to the chromedriver
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			// Create an instance of WebDriver
			
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			// Set the path to the firefoxdriver
			//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			// Create an instance of WebDriver
			driver = new FirefoxDriver();
		}

		// To maximize the Browser Window
		driver.manage().window().maximize();
		
		// Implicit Wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	// To open the Main Page URL
	public void openURL() {
//		prop = new Properties();
//
//		try {
//			prop.load(new FileInputStream("src/main/java/Config/config.properties"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String URL = (System.getProperty("URL") == null) ? prop.getProperty("websiteURLKey"):System.getProperty("URL");
		driver.get(URL);
	}
	
	// To Take Screenshots
	public void screenShot(String fileName) throws IOException {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		Long currentdateTime = System.currentTimeMillis();
		File destFile = new File(System.getProperty("user.dir") + "/Screenshot/" + currentdateTime + "_" + fileName + ".png");
		Files.copy(srcFile, destFile);
	}
	
	// To close the Browser
	public void closeBrowser() {
		driver.quit();
	}

}