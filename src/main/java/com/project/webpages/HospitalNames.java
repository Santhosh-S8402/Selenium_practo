package com.project.webpages;

import java.io.FileReader;
import java.util.List;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.json.simple.*;

import com.project.base.Base;

public class HospitalNames extends Base {
	public static JSONArray data = null;

	public JSONArray getDataFromJSON() {
		try {
			JSONParser parser = new JSONParser();
			FileReader reader = new FileReader(
			System.getProperty("user.dir") + "/src/test/resources/InputData.json");
			Object obj = parser.parse(reader);
			JSONObject obj1 = (JSONObject) obj;
			JSONArray data1 = (JSONArray) obj1.get("Data");
			data = data1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void getHospitalNames() throws InterruptedException {

		// get data from JSON
		data = getDataFromJSON();

		// To select the place as Bangalore
		try {
			WebElement location = driver.findElement(By.xpath("//input[@placeholder = 'Search location']"));
			location.clear();
			// To get location from json file
			JSONObject data1 = (JSONObject) data.get(0);
			String locationFromJSON = (String) data1.get("Location");

			location.sendKeys(locationFromJSON);
			driver.findElement(By.xpath("//div[text() = 'Bangalore']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// To Select a Hospitals in Search
		try {
			// To get search data from json file
			JSONObject data2 = (JSONObject) data.get(0);
			String searchFromJSON = (String) data2.get("Search");
			WebElement search = driver.findElement(By.xpath("//input[@placeholder = 'Search doctors, clinics, hospitals, etc.']"));
			search.sendKeys(searchFromJSON);
			driver.findElement(By.xpath("//div[@class = 'c-omni-suggestion-list']/div[1]//div[text() = 'Hospital']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Scroll Down the Page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(5000);
		
		// To find the Hospital Names with rating > 3.5 and Open 24/7
		List<WebElement> rating = driver.findElements(By.xpath("//div[@class  = 'c-feedback']//span[@class = 'u-bold']"));
		List<WebElement> hospital = driver.findElements(By.xpath("//div[@class  = 'c-estb-info']//a[@class = 'line-1']"));
		List<WebElement> openinghrs = driver.findElements(By.xpath("//div[@class  = 'c-estb-info']//span[@class = 'uv2-spacer--lg-left']/span"));
		System.out.println("========================================================");
		System.out.println("List of Hospital Names With Ratings >3.5 And Open 24/7");
		System.out.println("========================================================");
		int n = rating.size();
		for(int i=0; i < n ; i++)
		{
			float rate = Float.parseFloat(rating.get(i).getText());
			String openhours = openinghrs.get(i).getText();
			if(rate > 3.5 && openhours.equals("MON - SUN 00:00AM - 11:59PM"))
			{
				System.out.println(rating.get(i).getText() + " - " + hospital.get(i).getText());
			}
		}
		
		// Navigate to home page
		driver.navigate().back();
	}
}
