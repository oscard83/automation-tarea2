package com.globant.automation.tarea2.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchHotelPage extends BasePage{

	public SearchHotelPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "tab-hotel-tab-hp")
	private WebElement hotelTabButton;
	
	@FindBy(id = "hotel-destination-hp-hotel")
	private WebElement hotelDestinationField;
	
	@FindBy(id = "hotel-checkin-hp-hotel")
	private WebElement checkInField;
	
	@FindBy(id = "hotel-checkout-hp-hotel")
	private WebElement checkOutField;
	
	@FindBy(xpath = "//*[@id='hotel-checkin-wrapper-hp-hotel']/div/div/button[2]")
	private WebElement datePickerHotelPagingNextButton;

	@FindBy(xpath = "//*[@id='gcw-hotel-form-hp-hotel']/div[7]/label/button")
	private WebElement searchButton;
	
	String calendarHotelCheckInDates = "//*[@id='hotel-checkin-wrapper-hp-hotel']/div/div/div[2]/table/tbody//button";
	
	String calendarHotelCheckOutDates = "//*[@id='hotel-checkout-wrapper-hp-hotel']/div/div/div[2]/table/tbody//button";
	
	
	public void searchFlight(String destination, String currentDay, int qtyADT){
		wait.until(ExpectedConditions.elementToBeClickable(hotelDestinationField));
		hotelDestinationField.clear();
		hotelDestinationField.sendKeys(destination);
		hotelDestinationField.sendKeys(Keys.TAB);
		
		wait.until(ExpectedConditions.elementToBeClickable(checkInField));
		checkInField.click();
		datePickerHotelPagingNextButton.click();
		datePickerHotelPagingNextButton.click();
		selectDateInCalendar(currentDay, calendarHotelCheckInDates);
		
		Integer returnDay = new Integer(currentDay);
		returnDay = returnDay + 3;
		checkOutField.click();
		selectDateInCalendar(returnDay.toString(), calendarHotelCheckOutDates);
		
		searchButton.click();
		closeUndesiredPopUp();
	}
	
	private void selectDateInCalendar(String day, String Calendar){
		List<WebElement> allDates = driver.findElements(By.xpath(Calendar));
		
		for(WebElement element:allDates){			
			String date = element.getText();			
			if(date.equalsIgnoreCase(day)){
				element.click();
				break;
			}	
		}
	}
	
	public void clickHotelTabButton(){
		wait.until(ExpectedConditions.elementToBeClickable(hotelTabButton));
		hotelTabButton.click();
	}
	
	private void closeUndesiredPopUp(){
		String subWindowHandler = null;
		String mainWindow = driver.getWindowHandle();
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		
		if(handles.size()>1){
			while (iterator.hasNext())
			    subWindowHandler = iterator.next();
			
			driver.switchTo().window(subWindowHandler);
			driver.close();
			driver.switchTo().window(mainWindow);
		}
	}
}
