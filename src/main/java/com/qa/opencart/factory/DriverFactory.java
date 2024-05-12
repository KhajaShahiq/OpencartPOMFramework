package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	
	Properties prop;
	WebDriver driver;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight;
	
	public WebDriver initDriver(Properties prop)
	{
		
		String browserName= prop.getProperty("browser");
		//System.out.println("Browser is :  " +browserName);
		Log.info("Browser name is :  " +browserName);
		highlight = prop.getProperty("highlight");

		optionsManager=new OptionsManager(prop);
		
		switch(browserName.toLowerCase().trim())
		{
		case  "chrome":
			
		driver=new ChromeDriver(optionsManager.getChromeOptions());
		tlDriver.set(driver);
		break;
		
		case "firefox" :
		driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
		tlDriver.set(driver);
		break;
		
		case "edge" :
		driver =new EdgeDriver(optionsManager.getEdgeOptions());
		tlDriver.set(driver);
		break;
		
		
		default :
			//System.out.println("Pass right browser : " +browserName);
			Log.error("Pass right browser : " +browserName);
			throw new BrowserException("NO BROWSER FOUND"  +browserName);
		
		}
		
		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		
		return driver;
	}

	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp()
	{
		
		
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running tests on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("No env is given...hence running it on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name... " + envName);
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
		
		
		
		
		
		
//		prop=new Properties();
//		try {
//			FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return prop;
		
	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}



	
	
	}
	
	
	

