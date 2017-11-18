package com.globant.automation.tarea2.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchHotelResultsPage extends BasePage{

	String loadingMessage = "interstitial-message";

	@FindBy(id = "resultsContainer")
	private WebElement listOfResults;

	public SearchHotelResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean checkSponsoredHotel(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loadingMessage)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hotelResultTitle']/h1")));
		
		List<WebElement> hotelsCollection = listOfResults.findElements(By.className("flex-content"));
		
		String sponsoredText = null;
		sponsoredText = hotelsCollection.get(0).findElement(By.cssSelector("li.sponsoredListing.secondary")).getText();
		if(sponsoredText.length()>0)
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
	}
	
	public boolean checkDiscountHotel(){
		boolean checkResult = false;
		int countDiscounts = 0;
		
		List<WebElement> hotelsCollection = listOfResults.findElements(By.className("flex-content"));
		
		for(WebElement element:hotelsCollection){
			List<WebElement> discount = element.findElements(By.cssSelector("span.badge.badge-success.badge-secondary.badge-notification"));
			if(discount.size()>0)
				countDiscounts++;
		}
		
		if(countDiscounts>0)
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
	}
}
