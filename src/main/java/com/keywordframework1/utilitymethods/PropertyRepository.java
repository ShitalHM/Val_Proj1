package com.keywordframework1.utilitymethods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class PropertyRepository {
	/*accepts key from MyntraTest and fetch its respective values from OR.properties
	  and return to MyntraTest class from where that MyntraTest TC, it will call
	  click(),enterText(), etc methods.
	*/
	private String getValue(String locatorKey) {
		String dir=System.getProperty("user.dir");
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(dir+"\\src\\main\\resources\\OR.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop=new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value=prop.getProperty(locatorKey);
		return value;
	}
	public String getLocator(String key) {
		String val=getValue(key);
		return val;
	}
	
}
