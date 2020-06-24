package com.ecommerce.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ecommerce.pages.Homepage;

public class Compare2Products extends BaseTest{

	@Test
	public void productcomparion() {
		driver.get(prop.getProperty("Url"));
		//Step 2. Verify Title of the page
			String PageTitle = driver.getTitle();
			if(PageTitle.equals("Home page")) {
				
		 // Step 3. Click on ‘MOBILE’ menu
			Homepage hmpg = new Homepage(driver);
			hmpg.mobile();
			String MobilePageTitle = driver.getTitle();
			try {
				assertTrue(MobilePageTitle.equals("Mobile"));
			} catch (Exception e) {
				System.out.println("MobilePageTitle not matched");
			}
			
			
		//Add product to compare list
			String Mainmobile1 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
			String Mainmobile2 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
			driver.findElement(By.xpath("//a[text()='Sony Xperia']/../..//a[@class='link-compare']")).click();
			driver.findElement(By.xpath("//a[text()='IPhone']/../..//a[@class='link-compare']")).click();
			driver.findElement(By.xpath("//button[@title='Compare']")).click();
			
		//Switch to child window
			Set<String> wndwhnd = driver.getWindowHandles();
			Iterator<String> It= wndwhnd.iterator();
			String parentwindow = It.next();
			String childwindow = It.next();
			driver.switchTo().window(childwindow);
	   //
			String expTitleofchildwndw = driver.getTitle();
			String actTitleofchildwndw = "Products Comparison List - Magento Commerce";
			try {
				assertEquals(actTitleofchildwndw,expTitleofchildwndw);
			} catch (Exception e) {
				System.out.println("ChildWindowTitle not matched");
				//throw(e);
			}
	   //
			String Popupmob1 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
			String Popupmob2 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
			
	   //To check the 2 mobiles selected are the two in the popup - this is tp check the Sony Experia
			try {
				assertEquals(Mainmobile1,Popupmob1);
			} catch (Exception e) {
				System.out.println("Sony Experia is not present in PopupWindow");
			}
			
	  // // to check the 2 mobiles selected are the two in the popup - this is tp check the IPhone
			try {
				assertEquals(Mainmobile2,Popupmob2);
			} catch (Exception e) {
				System.out.println("Iphone is not present in PopupWindow");
			}
			
	  //close child window 
			driver.findElement(By.xpath("//button[@title='Close Window']")).click();
			driver.switchTo().window(parentwindow);
			
	}
  }
}