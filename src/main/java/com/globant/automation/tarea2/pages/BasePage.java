package com.globant.automation.tarea2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Pagina base de la que van a heredar todas las demas paginas que se van a mapear
 * */


public class BasePage {
	protected WebDriver driver;	
	protected WebDriverWait wait;

	public BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, 600);
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