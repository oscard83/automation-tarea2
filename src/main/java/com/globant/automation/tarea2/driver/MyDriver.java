package com.globant.automation.tarea2.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyDriver {
	private WebDriver driver = null;
	private DesiredCapabilities cap;
	
	public MyDriver(String browser){		
		cap = new DesiredCapabilities();
		
		switch(browser){
			case "Firefox":
				System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver.exe");
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver = new FirefoxDriver(cap);
				break;
			
			case "Chrome":
				System.setProperty("webdriver.ie.chrome", "C:\\selenium\\IEDriverServer.exe");				
				driver = new ChromeDriver(cap);
				break;
		}
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
}