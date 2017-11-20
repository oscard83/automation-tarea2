package com.globant.automation.tarea2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * Pagina que mapea la confirmacion del paquete que hemos elegido
 * */

public class ConfirmationPackagePage extends BasePage{

	@FindBy(className = "price-summary")
	private WebElement priceSummaryForTrip;
	
	@FindBy(className = "product-summary")
	private WebElement flightOptionsText;
	
	@FindBy(className = "traveler-info-header")
	private WebElement hotelText;
	
	@FindBy(id = "trip-summary")
	private WebElement tripSummary;
	
	@FindBy(id = "complete-booking")
	private WebElement continueBookingButton;
	
	
	public ConfirmationPackagePage(WebDriver driver) {
		super(driver);
	}
	
	public boolean checkConfirmationPage(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("trip-summary-header")));
		
		boolean checkResult = false;
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		
		String totalPriceForTripText;
		String flightOptions;
		String hotelOptions;
		String tripSummaryText;
		
		totalPriceForTripText = priceSummaryForTrip.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",flightOptionsText);
		flightOptions = flightOptionsText.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",hotelText);
		hotelOptions = hotelText.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",tripSummary);
		tripSummaryText = tripSummary.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",continueBookingButton);
		
		if(totalPriceForTripText.length()>0 && flightOptions.length()>0
			&& hotelOptions.length()>0 && tripSummaryText.length()>0
			&& continueBookingButton.isDisplayed())
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
	}
}