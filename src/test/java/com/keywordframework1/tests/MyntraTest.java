package com.keywordframework1.tests;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.keywordframework1.utilitymethods.Keywords.*;
import com.keywordframework1.constants.LocatorType;
import com.keywordframework1.constants.Locators;
import com.keywordframework1.utilitymethods.PropertyRepository;

public class MyntraTest extends BaseFramework{
	PropertyRepository prop;
	private static final Logger LOG = Logger.getLogger(MyntraTest.class);
	//@Test(priority=1)
	public void verifyLogo() {
		String currentUrl="https://www.myntra.com/";
		System.out.println("Myntra.com website opened successfully..");
		waitMethod(3000);
		//click("css","a.myntraweb-sprite");
		click(LocatorType.css,"a.myntraweb-sprite");
		String expectedUrl=getUrlMethod();
		System.out.println("Expected Url: "+expectedUrl);
		Assert.assertEquals(currentUrl, expectedUrl);
		System.out.println("LOGO is correctly verified..");
	}
	//@Test(priority=2)
	public void verifySearchForProductBrandAndMore() {
		
		//enterText(LocatorType.css,"input[placeholder*='Search for products']","Gifts");
		enterText(LocatorType.css,Locators.enterTextOnSearchBtn,"Gifts");
		System.out.println("Checked for gitfs..");
		waitMethod(3000);
		//click("css","span[data-reactid='882']");
		//Here LocatorType Interface for LocatorType is created & used
		click(LocatorType.css,"span[data-reactid='882']");
		
	}
	/*
	 * This method Uses Object Repository  
	 * Method-1 in which all locatorValues are stored in one filei.e Interface
	 * And access by importing that Interface in this class
	 * and by its name (like LocatorType in previous TC)
	 */
	//@Test(priority=3)
	public void verifyHomePageProfile() {
		click(Locators.clickOnProfileBtn);
		waitMethod(5000);
		click(Locators.clickOnLoginOrSignUpBtn);
		waitMethod(5000);
		enterText(Locators.enterMobileNumber,"0123456789");

	}
	/*
	 * This method Uses Object Repository/Locator Repository
	 * Method-1 in which all locatorValues are stored in one filei.e properties file
	 * Create a class to access the locators in .properies file
	 * In tests, call the methods of these class like we call method of Keywords class.
	 */
	@Test
	public void verifyHomePageProfileAgain() {
		prop=new PropertyRepository();
		click(prop.getLocator("clickOnProfileBtn"));
		//System.out.println("Clicked on Home page profile...");
		LOG.info("Clicked on Home page profile...");
		waitMethod(5000);
		click(prop.getLocator("clickOnLoginOrSignUpBtn"));
		//System.out.println("Clicked on Login button");
		LOG.info("Clicked on Login button");
		waitMethod(5000);
		enterText(prop.getLocator("enterMobileNumber"),"0123456789");
		//System.out.println("Entered Mobile no. successfuly");
		LOG.info("Entered Mobile no. successfuly");
	}
}