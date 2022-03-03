//myntra.com
package com.keywordframework1.utilitymethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Keywords {
	// CheckBrowser & openBrowser
	// launchUrl
	// Click
	// wait
	// enterText
	public static RemoteWebDriver driver;
	public static WebDriverWait wait;
	public static WebElement ele;
	public static Alert a;
	private static final Logger LOG = Logger.getLogger(Keywords.class);
	/**
	 * This method is used to launch browser
	 * <ul>
	 * <li>chrome</li>
	 * <li>firefox</li>
	 * <li>internet explorer</li>
	 * <li>opera</li>
	 * </ul>
	 * @param browser
	 */
	public static void openBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("internet explorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else {
			//System.out.println("Invalid Browser entered");
			LOG.info("Invalid Browser entered");
		}
		//System.out.println("Browser opened:" + browser);
		LOG.info("Browser opened:" + browser);
	}
	public static void launchUrl(String url) {
		driver.manage().window().maximize();
		driver.get(url);
	}
	public static void closeBrowser() {
		driver.quit();
	}
	@Deprecated
	public static void click(String locatorName, String locatorValue) {
		ele = getWebElement(locatorName, locatorValue);
		ele.click();
	}
	public static void click(String locatorValue) {
		String[] part=locatorValue.split("##");
		getWebElement(part[0], part[1]).click();
	}

	public static String getTextMethod(String locatorName, String locatorValue) {
		ele=getWebElement(locatorName, locatorValue);
		return ele.getText();
	}
	public static String getUrlMethod() {
		return driver.getCurrentUrl();
	}
	@Deprecated
	public static void enterText(String locatorName, String locatorValue, String text) {
		ele = getWebElement(locatorName, locatorValue);
		ele.sendKeys(text);
	}
	public static void enterText(String locatorValue, String text) {
		String[] part=locatorValue.split("##");
		System.out.println(part[0]+"\t"+part[1]);
		ele = getWebElement(part[0], part[1]);
		ele.sendKeys(text);
	}
	private static WebElement getWebElement(String locatorName, String locatorValue) {
		System.out.println("getWebElement:\n"+locatorName+"\t"+locatorValue);
		if (locatorName.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(locatorValue));
		} else if (locatorName.equalsIgnoreCase("css")) {
			return driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorName.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(locatorValue));
		} else if (locatorName.equalsIgnoreCase("className")) {
			return driver.findElement(By.className(locatorValue));
		} else {
			//System.out.println("Invalid Locator");
			LOG.info("Invalid Locator");
			return null;
		}
	}
	public static void waitMethod(long duration) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(duration));
	}
	public static void waitFor(String type,Duration duration){
		wait=prepareWait(duration);
		if(type.equalsIgnoreCase("alert")) {
			wait.until(ExpectedConditions.alertIsPresent());
		}else if(type.equalsIgnoreCase("click")) {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}else if(type.equalsIgnoreCase("select")) {
			wait.until(ExpectedConditions.elementToBeSelected(ele));
		}
	}

	private static WebDriverWait prepareWait(Duration duration) {
		wait = new WebDriverWait(driver, duration);
		wait.pollingEvery(Duration.ofMillis(100));
		wait.ignoring(ElementNotVisibleException.class);
		wait.withMessage("Element Not visible..");
		return wait;
	}
	public static void alertHandlingMethod() {
		driver.switchTo().alert().dismiss();
	}
}
