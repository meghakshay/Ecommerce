package com.ecommerce.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ecommerce.pages.Homepage;

public class CostCompareTest extends BaseTest{
	@Test
	public void comparecost() {
		driver.get(prop.getProperty("Url"));
		//Step 2. Verify Title of the page
			String PageTitle = driver.getTitle();
			if(PageTitle.equals("Home page")) {
		 // Step 3. Click on ‘MOBILE’ menu
				Homepage hmpg = new Homepage(driver);
				hmpg.mobile();
				String MobilePageTitle = driver.getTitle();
				assertTrue(MobilePageTitle.equals("Mobile"));
		// Step 5. In the list of all mobile , select ‘SORT BY’ dropdown as ‘name’
				Select oselect = new Select(driver.findElement(By.xpath("//select[@title='Sort By']")));
				oselect.selectByValue("http://live.demoguru99.com/index.php/mobile.html?dir=asc&order=name");
		//Step 6 Compare Mobile Cost
				String homepage_mobileCost = driver.findElement(By.xpath("//a[text()='Sony Xperia']/../..//span[@class='price']")).getText();
				driver.findElement(By.xpath("//a[text()='Sony Xperia']")).click();
				String detailpage_mobileCost = driver.findElement(By.xpath("//span[text()='Sony Xperia']/../..//span[@class='price']")).getText();
				
		// //  Product price in list and details page should be equal ($100)
				try {
					assertEquals(homepage_mobileCost, detailpage_mobileCost);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					System.out.println("homepage_mobileCost"+homepage_mobileCost);
					System.out.println("detailpage_mobileCost"+detailpage_mobileCost);
				}
	
			}
	
	}

}
