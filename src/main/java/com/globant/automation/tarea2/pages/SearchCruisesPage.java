package com.globant.automation.tarea2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchCruisesPage extends BasePage{

	public SearchCruisesPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(id = "cruise-destination-hp-cruise")
	private WebElement selectDest;
	private Select selectDestination = new Select(selectDest);
	
	@FindBy(id = "cruise-departure-month-hp-cruise")
	private WebElement selectMonth;
	private Select selectDepartureMonth = new Select(selectMonth);
	
	@FindBy(xpath = "//*[@id='gcw-cruises-form-hp-cruise']/button")
	private WebElement searchCruisesButton;
	
	@FindBy(id = "tab-cruise-tab-hp")
	private WebElement tabCruise;
	
	public void clickCruisesTab(){
		wait.until(ExpectedConditions.elementToBeClickable(tabCruise));
		tabCruise.click();
	}
	
	
	public void searchCruise(String destination, String departureMonth, int qtyADT){
		
		wait.until(ExpectedConditions.elementToBeClickable(selectDest));
		selectDest.click();
		selectDestination.selectByVisibleText(destination);
		
		wait.until(ExpectedConditions.elementToBeClickable(selectMonth));
		selectMonth.click();
		selectDepartureMonth.selectByVisibleText(departureMonth);
		
		wait.until(ExpectedConditions.elementToBeClickable(selectMonth));
		searchCruisesButton.click();
	}
}