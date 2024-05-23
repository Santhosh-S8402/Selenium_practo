package com.project.testSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.base.Base;
import com.project.webpages.CorporateWellness;
import com.project.webpages.HospitalNames;
import com.project.webpages.TopCities;

public class TestCases extends Base {
	HospitalNames hn = new HospitalNames();
	TopCities tc = new TopCities();
	CorporateWellness ca = new CorporateWellness();
	
	@Parameters("browserName")
	@BeforeTest
	public void invokeBrowser(String browserName) {
		hn.invokeBrowser();
	}

	@Test(priority = 1)
	public void hospitalNames() throws InterruptedException {
		hn.openURL();
		hn.getHospitalNames();
	}

	@Test(priority = 2)
	public void TopCities() throws IOException {
		tc.openURL();
		tc.getCities();
	}

	@Test(priority = 3)
	public void corporateWellness() throws InterruptedException, IOException {
		ca.openURL();
		ca.formFill();
	}

	@AfterTest
	public void closeBrowser() {
		ca.closeBrowser();
	}

}
