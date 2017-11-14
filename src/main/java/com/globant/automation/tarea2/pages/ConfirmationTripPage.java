package com.globant.automation.tarea2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * Pagina que mapea la confirmacion de los vuelos que hemos elegidos
 * */

public class ConfirmationTripPage extends BasePage{

	@FindBy(id = "totalPriceForTrip")
	private WebElement totalPriceForTrip;
	
	@FindBy(xpath = "//*[@id='preferences']/form/fieldset/h2")
	private WebElement flightOptionsText;
	
	@FindBy(xpath = "//*[@id='payments']/fieldset/h2")
	private WebElement paymentsText;
	
	@FindBy(xpath = "//*[@id='email']/div[1]/h3")
	private WebElement emailText;
	
	@FindBy(id = "complete-booking")
	private WebElement completeBookingButton;
	
	@FindBy(id = "modalCloseButton")
	private WebElement modalCloseButton;
	
	public ConfirmationTripPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean checkConfirmationPage(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("air-delayed-prompt-modal-id-title")));
		modalCloseButton.click();
		
		boolean checkResult = false;
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		
		String totalPriceForTripText;
		String flightOptions;
		String payments;
		String email;
		
		totalPriceForTripText = totalPriceForTrip.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",flightOptionsText);
		flightOptions = flightOptionsText.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",paymentsText);
		payments = paymentsText.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",emailText);
		email = emailText.getText();
		
		js.executeScript("arguments[0].scrollIntoView(true);",completeBookingButton);
		
		if(totalPriceForTripText.length()>0 && flightOptions.length()>0
			&& payments.length()>0 && email.length()>0
			&& completeBookingButton.isDisplayed())
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
	}
}