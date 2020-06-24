package com.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	WebDriver ldriver;
	public Homepage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(ldriver, this);
	}
	@FindBy(how=How.XPATH, using="//a[text()='Mobile']")
	@CacheLookup
	WebElement Mobile;
	
	@FindBy(how=How.XPATH, using="//a[text()='TV']")
	@CacheLookup
	WebElement Tv;
	
	public void mobile() {
		Mobile.click();
	}
	
	public void tv() {
		Tv.click();
	}

}
