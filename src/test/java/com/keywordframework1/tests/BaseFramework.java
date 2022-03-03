package com.keywordframework1.tests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import static com.keywordframework1.utilitymethods.Keywords.*;
public class BaseFramework {
	@Parameters({"browserName","url"})
	@BeforeMethod
	public void setup(String browserName, String url) {
		openBrowser(browserName);	
		launchUrl(url);
	}
	@AfterMethod
	public void tearDown() {
		closeBrowser();
	}
}
