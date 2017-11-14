package com.globant.automation.tarea2.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/*
 * Pagina que mapea los resultados de la busqueda de vuelos
 * */

public class SearchResultsPage extends BasePage{

	@FindBy(id = "flightModuleList")
	private WebElement listOfResults;
		
	@FindBy(id = "forcedChoiceNoThanks")
	private WebElement noThanksLink;
	
	@FindBy(name = "sort")
	private WebElement select;
	private Select selectSortingResults = new Select(select);
	
	String progressBar = "//*[@id='pi-interstitial']/div/div";
	
	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean checkSearchResults(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(progressBar)));
		
		List<WebElement> flightsCollection = listOfResults.findElements(By.className("flight-module"));
		
		for(WebElement element:flightsCollection){			
			WebElement selectFlightButton = element.findElement(By.className("t-select-btn"));			
			WebElement flightDurationText = element.findElement(By.className("duration-emphasis"));
			WebElement flightDetailLink = element.findElement(By.className("show-flight-details"));
			
			if(selectFlightButton.isDisplayed() && !flightDurationText.getText().isEmpty() &&
					flightDetailLink.isDisplayed()){
				checkResult = true;
			}else{
				checkResult = false;
				break;
			}
		}
		return checkResult;
	}
	
	public void orderSearchResults(String criteria){
		wait.until(ExpectedConditions.elementToBeClickable(select));
		select.click();
		selectSortingResults.selectByVisibleText(criteria);
		wait.until(ExpectedConditions.elementToBeClickable(select));
	}
	
	public boolean checkResultsOrderedList(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(progressBar)));
		
		List<WebElement> flightsCollection = listOfResults.findElements(By.className("flight-module"));
		int previousDuration = 0;
		
		for(WebElement element:flightsCollection){							
			WebElement flightDurationText = element.findElement(By.className("duration-emphasis"));
			String duration = flightDurationText.getText().trim();
			int indexHours = duration.indexOf("h");
			int hours = Integer.parseInt(duration.substring(0, indexHours));
			
			duration = duration.substring(duration.indexOf(" "));
			duration = duration.replace(" ", "");
			duration = duration.replace("m", "");
			int minutes = Integer.parseInt(duration);
			
			int currentDuration = (hours*60)+minutes;
			
			if(previousDuration <= currentDuration){
				checkResult = true;
				previousDuration = currentDuration;
			}
			else{
				checkResult = false;
				break;
			}
		}
		return checkResult;
	}
	
	public void selectFlight(int index){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(progressBar)));
		
		List<WebElement> flightsCollection = listOfResults.findElements(By.className("flight-module"));
		WebElement element = flightsCollection.get(index);
		wait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.className("t-select-btn"))));
		WebElement selectFlightButton = element.findElement(By.className("t-select-btn"));
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",selectFlightButton);
		
		selectFlightButton.click();
	}
	
	public void clickNoThanks(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forcedChoiceNoThanks")));
		
		if(noThanksLink.isDisplayed())
			noThanksLink.click();
		
		switchToPopUp();
	}
	
	private void switchToPopUp(){
		String subWindowHandler = null;
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		
		while (iterator.hasNext())
		    subWindowHandler = iterator.next();
		
		driver.close();
		driver.switchTo().window(subWindowHandler);
	}
}