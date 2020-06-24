package com.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Mobilepage {
	WebDriver driver;
	public Mobilepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(how=How.XPATH, using="//select[@title='Sort By']")
	@CacheLookup
	WebElement Sort_By;
	
	public WebElement sort_by() {
		return Sort_By;
	}
}
