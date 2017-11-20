package com.globant.automation.tarea2.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchCruisesResultsPage extends BasePage{

	public SearchCruisesResultsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "main-results")
	private WebElement listOfResults;
	
	@FindBy(className = "h1.cruise-page-header.universalFilterAriaHidden")
	private WebElement cruiseResultsNumber;
	
	@FindBy(id = "destination-select")
	private WebElement destinationSelected;
	
	@FindBy(id = "departure-month-select")
	private WebElement monthSelected;
	
	@FindBy(id = "length-10-14")
	private WebElement cruiseLenght;
	
	public boolean checkFilteredCruises(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("destination-select")));
		
		if(destinationSelected.getText().equals("Europe") && monthSelected.getText().equals("May 2018"))
			checkResult = true;
		else
			checkResult = false;
		
		return checkResult;
	}

	public void selectCruiseLenght(){
		wait.until(ExpectedConditions.elementToBeClickable(cruiseLenght));
		cruiseLenght.click();
		wait.until(ExpectedConditions.elementToBeClickable(cruiseLenght));
	}
	
	public boolean checkDiscuountsInCruises(){
		boolean checkResult = false;
		
		List<WebElement> cruisesCollection = listOfResults.findElements(By.className("flex-card"));
		
		for(WebElement element:cruisesCollection){
			WebElement price = element.findElement(By.className("card-price"));
			List<WebElement> originalPrices = element.findElements(By.className("strikeout-price-card"));
			
			if(price.getText().length() > 0){
				if(originalPrices.size() > 0){
					WebElement originalPrice = originalPrices.get(0);
					if(originalPrice.getText().length() > 0)
						checkResult = true;
				}
				checkResult = true;
			}else{
				checkResult = false;
			}				
		}
		return checkResult;
	}
	
	public void selectOptionWithHighestDiscount(){
		
		List<WebElement> cruisesCollection = listOfResults.findElements(By.className("flex-card"));
		WebElement highestDiscount = null;
		int discount = 0;
		
		for(WebElement element:cruisesCollection){
			WebElement price = element.findElement(By.className("card-price"));
			List<WebElement> originalPrices = element.findElements(By.className("strikeout-price-card"));
			
			String priceStr = price.getText();
			if(priceStr.length() > 0){
				if(originalPrices.size() > 0){
					WebElement originalPrice = originalPrices.get(0);
					String originalPriceStr = originalPrice.getText();
					if(originalPriceStr.length() > 0){
						priceStr = priceStr.replace(",", "");
						priceStr = priceStr.replace("$", "");
						originalPriceStr = originalPriceStr.replace(",", "");
						originalPriceStr = originalPriceStr.replace("$", "");
						if((Integer.parseInt(originalPriceStr) - Integer.parseInt(priceStr)) > discount){
							highestDiscount = element;
							discount = Integer.parseInt(originalPriceStr) - Integer.parseInt(priceStr);
						}
							
					}						
				}
			}		
		}
		WebElement showDatesButton = highestDiscount.findElement(By.className("show-dates-button"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",showDatesButton);
		showDatesButton.click();
	}
}
