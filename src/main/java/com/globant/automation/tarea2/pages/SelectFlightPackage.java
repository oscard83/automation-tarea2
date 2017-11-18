package com.globant.automation.tarea2.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectFlightPackage extends BasePage{
	
	public SelectFlightPackage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "flight-listing-container")
	private WebElement listOfResults;
	
	public void selectFlight(int index){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progress-bar")));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul#flightModuleList.segmented-list.results-list.dps-variant2.price-sort")));
		
		List<WebElement> flightsCollection = listOfResults.findElements(By.className("t-select-btn"));
		WebElement selectedFlight = flightsCollection.get(index);
		js.executeScript("arguments[0].scrollIntoView(true);",selectedFlight);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sortBar']/div/fieldset/label/select")));
		wait.until(ExpectedConditions.elementToBeClickable(selectedFlight));
		
		selectedFlight.click();
		
	}
}
