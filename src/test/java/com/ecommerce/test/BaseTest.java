package com.ecommerce.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.io.Files;

public class BaseTest {
	public static WebDriver driver;
	public FileInputStream file;
	public static Properties prop;
	@BeforeMethod
	public WebDriver driverinitiallation() {
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\ecommerce\\resources\\Data.Properties");
			prop = new Properties();
			prop.load(file);
		} catch (Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
		//get Browser Name from Data file
				String BrowserName=prop.getProperty("Browser");
				
				if(BrowserName.equals("Chrome")) {
					System.setProperty("webdriver.chrome.driver",prop.getProperty("ChromePath"));
					driver = new ChromeDriver();
					//log.info("Browser is Opened");
				}
				else if(BrowserName.equals("FireFox")){
					System.setProperty("webdriver.gecko.driver",prop.getProperty("FireFoxPath"));
					driver = new FirefoxDriver();
					//log.info("Browser is Opened");
				}
//				else {
//					System.setProperty("","");
//					driver = new InternetExplorerDriver();
//				}
				
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return driver;
				
	}
	
	@AfterMethod
	public void closing() {
		driver.close();
	}
	
	public void Capturesrcshot(WebDriver driver, String tname) throws IOException {
		TakesScreenshot srcst = (TakesScreenshot) driver;
		File srcfile = srcst.getScreenshotAs(OutputType.FILE);
		String Date_of_scr_shot = current_date();
		File dscfile = new File("D:\\Testing\\eclipse-workspace\\Ecommerce\\Screenshots\\"+tname+"_"+Date_of_scr_shot+".png");
		FileUtils.copyFile(srcfile,dscfile);
	}
	public static String current_date(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		return formattedDate;
		
	}
	public String Alphanumericstring() {
		String randomstring = RandomStringUtils.randomAlphanumeric(6);
		return randomstring;
	}
	
	public String NumericString() {
		return RandomStringUtils.randomNumeric(6);
		
	}
}
