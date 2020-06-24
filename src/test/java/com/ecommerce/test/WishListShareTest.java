package com.ecommerce.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ecommerce.pages.Homepage;

public class WishListShareTest extends BaseTest{

	@Test
	public void productcomparion() {
		driver.get(prop.getProperty("Url"));
		//Step 2. Verify Title of the page
			String PageTitle = driver.getTitle();
			if(PageTitle.equals("Home page")) {
		//
			driver.findElement(By.xpath("//a[contains(@class,'skip-link skip-account')]//span[text()='Account']")).click();
			driver.findElement(By.xpath("//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
	   //Fill Resigration Form
			String emailvalue = Alphanumericstring();
			String pwdvalue = NumericString();
			driver.findElement(By.id("firstname")).clear();
			driver.findElement(By.id("firstname")).sendKeys("Megha");
			driver.findElement(By.id("lastname")).clear();
			driver.findElement(By.id("lastname")).sendKeys("Vyas");
			driver.findElement(By.id("email_address")).clear();
			driver.findElement(By.id("email_address")).sendKeys(emailvalue+"@gmail.com");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(pwdvalue);
			driver.findElement(By.id("confirmation")).clear();
			driver.findElement(By.id("confirmation")).sendKeys(pwdvalue);
			driver.findElement(By.xpath("//button[@title='Register']")).click();
		//Verify Registration
			String Actaul_Registration_msg = driver.findElement(By.xpath("//p[@class='hello']//strong")).getText();
			String Exp_Registration_msg = "Hello, Megha Vyas";
			
			try {
				assertEquals(Actaul_Registration_msg, Exp_Registration_msg);
			} catch (Exception e) {
				System.out.println("There is already an account with this email address.");
			}
			
		 // Step 3. Click on ‘MOBILE’ menu
			Homepage hmpg = new Homepage(driver);
			hmpg.tv();
			String TvPageTitle = driver.getTitle();
			try {
				assertTrue(TvPageTitle.equals("TV"));
			} catch (Exception e) {
				System.out.println("TvPageTitle not matched");
			}
		
		//Step 4. Add product to wishlist
			driver.findElement(By.xpath("//h2//a[@title='LG LCD']/../..//a[@class='link-wishlist']")).click();
			String actualproductmsg = driver.findElement(By.xpath("//ul[@class='messages']/..//span[contains(text(),'LG LCD has been added to your wishlist. Click ')]")).getText();
			String expproductmsg = "LG LCD has been added to your wishlist. Click here to continue shopping.";
			try {
				assertEquals(actualproductmsg,expproductmsg);
			} catch (Exception e) {
				System.out.println("Product not added to wishlist");
			}
			driver.findElement(By.xpath("//button[@name='save_and_share']")).click();
			driver.findElement(By.id("email_address")).clear();
			driver.findElement(By.id("email_address")).sendKeys(emailvalue+"@gmail.com");
			driver.findElement(By.id("message")).clear();
			driver.findElement(By.id("message")).sendKeys("Hey Sonia! Please check LG TV");
			driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
			String actshreadMsg= driver.findElement(By.xpath("//ul[@class='messages']/..//span[contains(text(),'shared.')]")).getText();
			String expshredMsg  = "Your Wishlist has been shared.";
			try {
				assertEquals(actshreadMsg,expshredMsg);
			} catch (Exception e) {
				System.out.println("Wishlist not shared");
			}
		}
	}
}
