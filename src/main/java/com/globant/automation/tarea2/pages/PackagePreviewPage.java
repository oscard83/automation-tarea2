package com.globant.automation.tarea2.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PackagePreviewPage extends BasePage{

	@FindBy(id = "dxGroundTransportationModule")
	private WebElement carsResults;
	
	@FindBy(id = "tripTotal")
	private WebElement totalPrice;
	
	@FindBy(id = "departure-dates")
	private WebElement packageDates;
	
	@FindBy(id = "flight-header-traveler")
	private WebElement travellerInfo;
	
	@FindBy(id = "flight-card-0")
	private WebElement departureFlight;

	@FindBy(id = "flight-card-1")
	private WebElement arrivalFlight;
	
	@FindBy(xpath ="//*[@id='FlightUDPBookNowButton1']/button")
	private WebElement continueBookingButton;
	
	public PackagePreviewPage(WebDriver driver) {
		super(driver);
	}

	public void selectCar(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.section-header-main.header-class")));
		
		List<WebElement> carsCollection = carsResults.findElements(By.className("gt-add-btn"));
		carsCollection.get(0).click();
	}
	
	public boolean checkReviewPage(){
		boolean checkResult = false;
		
		if(totalPrice.getText().length()>0 && packageDates.getText().length()>0 && travellerInfo.getText().length()>0 
				&& departureFlight.getText().length()>0 && arrivalFlight.getText().length()>0){
			checkResult = true;
		}else
			checkResult = false;
		
		return checkResult;
	}
	
	public void continueBooking(){
		continueBookingButton.click();
	}
}