package com.project.testSuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.base.Base;
import com.project.webpages.CorporateWellness;
import com.project.webpages.HospitalNames;
import com.project.webpages.TopCities;

public class SmokeTesting extends Base {
	HospitalNames hn = new HospitalNames();
	TopCities tc = new TopCities();
	CorporateWellness ca = new CorporateWellness();
	@Parameters("browserName")
	@BeforeTest
	public void invokeBrowser(String browserName) {
		hn.invokeBrowser();
		hn.openURL();
	}

	@Test
	public void testing() throws InterruptedException {
		hn.getHospitalNames();
	}

	@AfterTest
	public void closeBrowser() {
		ca.closeBrowser();
	}

}
