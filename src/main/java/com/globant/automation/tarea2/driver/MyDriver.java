package com.globant.automation.tarea2.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyDriver {
	private WebDriver driver = null;
	
	public MyDriver(String browser){		
		
		switch(browser){
			case "Firefox":
				System.setProperty("webdriver.gecko.driver", "C:\\selenium_ff\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
		}
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
}