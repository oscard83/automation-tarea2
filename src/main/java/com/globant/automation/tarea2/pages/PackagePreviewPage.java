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
	
	@FindBy(className = "prod-total")
	private WebElement totalPrice;
	
	@FindBy(className = "travel-date")
	private WebElement packageDates;
	
	@FindBy(css = "h2.title-main")
	private WebElement travellerInfo;
		
	@FindBy(id ="complete-booking")
	private WebElement continueBookingButton;
	
	public PackagePreviewPage(WebDriver driver) {
		super(driver);
	}

	public void selectCar(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.section-header-main.header-class")));
		
		List<WebElement> carsCollection = carsResults.findElements(By.className("gt-add-btn"));
		if(carsCollection.size() > 0)
			carsCollection.get(0).click();
	}
	
	public boolean checkReviewPage(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.section-header-main.page-title")));
		boolean checkResult = false;
		
		if(totalPrice.getText().length()>0 && packageDates.getText().length()>0 && travellerInfo.getText().length()>0){
			checkResult = true;
		}else
			checkResult = false;
		
		return checkResult;
	}
	
	public void continueBooking(){
		continueBookingButton.click();
	}
}