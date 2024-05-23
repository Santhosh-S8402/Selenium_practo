package com.project.webpages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.project.base.Base;

public class TopCities extends Base {

	public void getCities() throws IOException {

		//Locate LabTests element in nav bar
		WebElement LabTests = driver.findElement(By.xpath("//div[@class = 'nav-mid']//div[text() = 'Lab Tests']"));
		LabTests.click();
		
		//Print all the Top Cities
		screenShot("TopCitiesPage");
		List<WebElement> topCities = driver.findElements(By.xpath("//ul[@class = 'u-br-rule u-marginb--std-half u-pointer u-padb--dbl o-flex o-flex__justify--between']/li/div[2]"));
		System.out.println("========================================================");
		System.out.println("All the top Cities in Diagnostics page");
		System.out.println("========================================================");
		for(WebElement city : topCities)
		{
			System.out.println(city.getText());
		}
		
		//Navigate to home page
		driver.navigate().back();
	}
}