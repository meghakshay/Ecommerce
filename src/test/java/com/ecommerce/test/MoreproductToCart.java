package com.ecommerce.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ecommerce.pages.Homepage;

public class MoreproductToCart extends BaseTest{
	@Test
	public void moreProductToCart() throws InterruptedException {
		driver.get(prop.getProperty("Url"));
		//Step 2. Verify Title of the page
			String PageTitle = driver.getTitle();
			if(PageTitle.equals("Home page")) {
		 // Step 3. Click on ‘MOBILE’ menu
				Homepage hmpg = new Homepage(driver);
				hmpg.mobile();
				String MobilePageTitle = driver.getTitle();
				assertTrue(MobilePageTitle.equals("Mobile"));
		//Step 4. Click on Add to cart
				driver.findElement(By.xpath("//a[text()='Sony Xperia']/../..//button[contains(@class,'btn-cart')]")).click();
				String shopping_page_Title = driver.getTitle();
				assertEquals(shopping_page_Title, "Shopping Cart");
		//Step 5.
				driver.findElement(By.xpath("//input[contains(@class,'qty') and @title='Qty']")).clear();
				driver.findElement(By.xpath("//input[contains(@class,'qty') and @title='Qty']")).sendKeys("1000");
				driver.findElement(By.xpath("//a[text()='Remove Item']/../..//span[text()='Update']")).click();
		
		//Step6.Comparing 
				String ActualErrorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
				String ExpErrorMsg= "The requested quantity for \\\"Sony Xperia\\\" is not available.";
				
				try {
					assertEquals(ActualErrorMsg,ExpErrorMsg);
				} catch (AssertionError e) {
					//e.printStackTrace();
					System.out.println("ExpErrorMsg = "+ExpErrorMsg);
				}
				
	   //Step7 Empty Cart
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[@id='empty_cart_button']")).click();
				String expnoitemMsg = "You have no items in your shopping cart.";
				String ActualNoItemMs = driver.findElement(By.xpath("//button[@id='empty_cart_button']")).getText();
				
				try {
					assertEquals(ActualNoItemMs,expnoitemMsg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("expnoitemMsg = "+expnoitemMsg);
				System.out.println("ActualNoItemMs = "+ActualNoItemMs);
		}
	}
}