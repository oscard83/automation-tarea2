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
	
	@FindBy(id = "tab-package-tab-hp")
	private WebElement flightHotelTabButton;
	
	@FindBy(id = "flight-origin-hp-flight")
	private WebElement originField;
	
	@FindBy(id = "package-origin-hp-package")
	private WebElement originPackageField;
	
	@FindBy(id = "flight-destination-hp-flight")
	private WebElement destinationField;
	
	@FindBy(id = "package-destination-hp-package")
	private WebElement destinationPackageField;
	
	@FindBy(id = "flight-departing-hp-flight")
	private WebElement dateDepartureField;
	
	@FindBy(id = "package-departing-hp-package")
	private WebElement dateDeparturePackageField;
	
	@FindBy(id = "flight-returning-hp-flight")
	private WebElement dateArrivalField;
	
	@FindBy(id = "package-returning-hp-package")
	private WebElement dateArrivalPackageField;
	
	@FindBy(id = "partialHotelBooking-hp-package")
	private WebElement partialHotelBooking;
	
	@FindBy(id = "package-checkin-hp-package")
	private WebElement partialStayHotelBooking;
		
	@FindBy(xpath = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/button[2]")
	private WebElement datePickerPagingNextButton;
	
	@FindBy(xpath = "//*[@id='package-departing-wrapper-hp-package']/div/div/button[2]")
	private WebElement datePickerPackagePagingNextButton;
	
	@FindBy(xpath = "//*[@id='package-checkin-wrapper-hp-package']/div/div/button[2]")
	private WebElement datePickerPartialStayPagingNextButton;
	
	String calendarDepartureDates = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/div[2]/table/tbody//button";
	
	String calendarArrivalDates = "//*[@id='flight-returning-wrapper-hp-flight']/div/div/div[2]/table/tbody//button";

	String calendarPackageDepartureDates = "//*[@id='package-departing-wrapper-hp-package']/div/div/div[2]/table/tbody//button";
	
	String calendarPackageArrivalDates = "//*[@id='package-returning-wrapper-hp-package']/div/div/div[2]/table/tbody//button";
	
	String calendarPartialStayCheckInDates = "//*[@id='package-checkin-wrapper-hp-package']/div/div/div[2]/table/tbody//button";
	
	@FindBy(xpath = "//*[@id='gcw-packages-form-hp-package']/div[1]")
	private WebElement partialStayErrorMessage;
	
	@FindBy(xpath = "//*[@id='gcw-flights-form-hp-flight']/div[7]/label/button")
	private WebElement searchFlightButton;
	
	@FindBy(id = "search-button-hp-package")
	private WebElement searchPackageButton;
	
	public void clickFlightsTabButton(){
		wait.until(ExpectedConditions.elementToBeClickable(flightTabButton));
		flightTabButton.click();
	}
	
	public void clickFlightsAndHotelTabButton(){
		wait.until(ExpectedConditions.elementToBeClickable(flightHotelTabButton));
		flightHotelTabButton.click();
	}
	
	public void searchFlight(String origin, String destination, String currentDay, int qtyADT){
		wait.until(ExpectedConditions.elementToBeClickable(originField));
		originField.clear();
		originField.sendKeys(origin);
		
		wait.until(ExpectedConditions.elementToBeClickable(destinationField));
		destinationField.clear();
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
		
		searchFlightButton.click();
		closeUndesiredPopUp();
	}
	
	public void searchFlightHotel(String origin, String destination, String currentDay, int qtyADT){
		wait.until(ExpectedConditions.elementToBeClickable(originPackageField));
		originPackageField.clear();
		originPackageField.sendKeys(origin);
		
		wait.until(ExpectedConditions.elementToBeClickable(destinationPackageField));
		destinationPackageField.clear();
		destinationPackageField.sendKeys(destination);
		
		wait.until(ExpectedConditions.elementToBeClickable(dateDeparturePackageField));
		dateDeparturePackageField.click();
		datePickerPackagePagingNextButton.click();
		datePickerPackagePagingNextButton.click();
		selectDateInCalendar(currentDay, calendarPackageDepartureDates);
		
		Integer returnDay = new Integer(currentDay);
		returnDay = returnDay + 13;
		dateArrivalPackageField.click();
		selectDateInCalendar(returnDay.toString() , calendarPackageArrivalDates);
		
		searchPackageButton.click();
	}
	
	public void searchHotelPartialStay(String origin, String destination, String currentDay, int qtyADT){
		wait.until(ExpectedConditions.elementToBeClickable(originPackageField));
		originPackageField.clear();
		originPackageField.sendKeys(origin);
		
		wait.until(ExpectedConditions.elementToBeClickable(destinationPackageField));
		destinationPackageField.clear();
		destinationPackageField.sendKeys(destination);
		
		wait.until(ExpectedConditions.elementToBeClickable(dateDeparturePackageField));
		dateDeparturePackageField.click();
		datePickerPackagePagingNextButton.click();
		datePickerPackagePagingNextButton.click();
		selectDateInCalendar(currentDay, calendarPackageDepartureDates);
		
		Integer returnDay = new Integer(currentDay);
		returnDay = returnDay + 13;
		dateArrivalPackageField.click();
		selectDateInCalendar(returnDay.toString() , calendarPackageArrivalDates);
		
		partialHotelBooking.click();
		partialStayHotelBooking.click();
		datePickerPartialStayPagingNextButton.click();
		datePickerPartialStayPagingNextButton.click();
		selectDateInCalendar(returnDay.toString() , calendarPartialStayCheckInDates);
		
		searchPackageButton.click();
	}
	
	public boolean checkPartialStayErrorMessage(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.visibilityOf(partialStayErrorMessage));
		
		if(partialStayErrorMessage.getText().length()>0)
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
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
