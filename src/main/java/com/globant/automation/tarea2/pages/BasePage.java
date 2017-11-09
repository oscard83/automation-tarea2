package com.globant.automation.tarea2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;	
	protected WebDriverWait wait;

	public BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, 240);
	}
	
	protected WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}
	
	public void dispose(){
		if(driver!=null)
			driver.close();
	}
	
}