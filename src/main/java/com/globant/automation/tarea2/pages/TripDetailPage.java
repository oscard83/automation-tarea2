package com.globant.automation.tarea2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * Pagina que mapea el detalle de los vuelos que el usuario ha elegido
 * */

public class TripDetailPage extends BasePage{

	@FindBy(id = "bookButton")
	private WebElement bookButton;
	
	@FindBy(className = "packagePriceTotal")
	private WebElement packagePriceTotalText;
	
	@FindBy(className = "OD0")
	private WebElement departureInfoText;
	
	@FindBy(className = "OD1")
	private WebElement arrivalInfoText;
	
	@FindBy(className = "priceGuarantee")
	private WebElement priceGuaranteeText;
	
	String parentWindowHandler = null;
	
	public TripDetailPage(WebDriver driver) {
		super(driver);
		parentWindowHandler = driver.getWindowHandle();
	}
	
	public boolean checkTripDetails(){
		boolean checkResult = false;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("section-header-main")));
		
		if(packagePriceTotalText.getText().length()>0 && departureInfoText.getText().length()>0 
				&& arrivalInfoText.getText().length()>0 && priceGuaranteeText.getText().length()>0)
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
	}
	
	public void clickBookButton(){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",bookButton);
		wait.until(ExpectedConditions.elementToBeClickable(bookButton));
		bookButton.click();
	}
}