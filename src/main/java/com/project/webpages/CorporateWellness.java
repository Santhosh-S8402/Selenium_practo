package com.project.webpages;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.base.Base;

public class CorporateWellness extends Base {
	WebElement name, orgName, contact, email;
    
	@SuppressWarnings("resource")
	public void formFill() throws InterruptedException, IOException {

		// Initializing the Excel Sheet
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/FormData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("DataForFormFilling");
		XSSFRow row = sheet.getRow(0);
		
		// Selecting the Corporate Wellness page
		driver.findElement(By.xpath("//div[@class = 'c-footer__content']/div[4]/div[2]/div[2]/a")).click();

		// Switching to new tab
		Set<String> currentHandle = driver.getWindowHandles();
		Iterator<String> iterator = currentHandle.iterator();
		iterator.next();
		String corporate = iterator.next();
		driver.switchTo().window(corporate);
		
		// Filling the form
		
		// To locate the Name WebElement
		name = driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//input[@id = 'name']"));
		// To locate the Organization Name WebElement
		orgName = driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//input[@id = 'organizationName']"));
		// To locate the Contact Number WebElement
		contact = driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//input[@id = 'contactNumber']"));
		// To locate the Email WebElement
		email = driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//input[@id = 'officialEmailId']"));
		
		// Send the Data to the WebElements
		name.sendKeys(row.getCell(0).getStringCellValue());
		orgName.sendKeys(row.getCell(1).getStringCellValue());
		contact.sendKeys("" + (long) row.getCell(2).getNumericCellValue());
		email.sendKeys(row.getCell(3).getStringCellValue());
		screenShot("SubmitbuttonNotEnabled");
		// Select the DropDown element
		Select orgsizedropDown = new Select(driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//select[@id = 'organizationSize']")));
		orgsizedropDown.selectByVisibleText(row.getCell(4).getStringCellValue());
		Select interestIndropDown = new Select(driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//select[@id = 'interestedIn']")));
		interestIndropDown.selectByVisibleText(row.getCell(5).getStringCellValue());
		screenShot("FormdataEntered");
		driver.findElement(By.xpath("//div[@class = 'corporate-page']/header[1]//button")).click();
		Thread.sleep(5000);
		screenShot("FormdataSubmitted");
	}
}
