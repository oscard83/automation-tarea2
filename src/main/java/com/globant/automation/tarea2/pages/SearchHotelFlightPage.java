package com.globant.automation.tarea2.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * Pagina donde inicia la prueba, en esta se selecciona el tipo de busqueda
 * y tambien los parametros necesarios para iniciar la busqueda
 * */

public class SearchHotelFlightPage extends BasePage{
	
	public SearchHotelFlightPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "tab-flight-tab-hp")
	private WebElement flightTabButton;
	
	@FindBy(id = "flight-origin-hp-flight")
	private WebElement originField;
	
	@FindBy(id = "flight-destination-hp-flight")
	private WebElement destinationField;
	
	@FindBy(id = "flight-departing-hp-flight")
	private WebElement dateDepartureField;
	
	@FindBy(id = "flight-returning-hp-flight")
	private WebElement dateArrivalField;
	
	@FindBy(id = "flight-adults-hp-flight")
	private WebElement qtyADTSelect;
	
	@FindBy(id = "flight-children-hp-flight")
	private WebElement qtyCHDSelect;
	
	@FindBy(xpath = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/button[2]")
	private WebElement datePickerPagingNextButton;
	
	String calendarDepartureDates = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/div[2]/table/tbody//button";
	
	String calendarArrivalDates = "//*[@id='flight-returning-wrapper-hp-flight']/div/div/div[2]/table/tbody//button";

	@FindBy(xpath = "//*[@id='gcw-flights-form-hp-flight']/div[7]/label/button")
	private WebElement searchButton;
	
	
	public void clickFlightsTabButton(){
		wait.until(ExpectedConditions.elementToBeClickable(flightTabButton));
		flightTabButton.click();
	}
	
	public void searchFlight(String origin, String destination, String currentDay, int qtyADT){
		wait.until(ExpectedConditions.elementToBeClickable(originField));
		originField.sendKeys(origin);
		
		wait.until(ExpectedConditions.elementToBeClickable(destinationField));
		destinationField.sendKeys(destination);
		
		wait.until(ExpectedConditions.elementToBeClickable(dateDepartureField));
		dateDepartureField.click();
		datePickerPagingNextButton.click();
		datePickerPagingNextButton.click();
		selectDateInCalendar(currentDay, calendarDepartureDates);
		
		Integer returnDay = new Integer(currentDay);
		returnDay = returnDay + 7;
		dateArrivalField.click();
		selectDateInCalendar(returnDay.toString() , calendarArrivalDates);
		
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
