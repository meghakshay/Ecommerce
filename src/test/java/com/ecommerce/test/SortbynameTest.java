package com.ecommerce.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ecommerce.pages.Homepage;

public class SortbynameTest extends BaseTest{
	@Test
	public void Mobilesort() throws IOException {
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
	// Step 6. Verify all products are sorted by name
			Capturesrcshot(driver,"Mobilesort");
			
		}
	}
}
