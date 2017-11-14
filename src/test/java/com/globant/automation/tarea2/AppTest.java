package com.globant.automation.tarea2;


import java.util.Calendar;
import java.util.GregorianCalendar;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.globant.automation.tarea2.driver.MyDriver;
import com.globant.automation.tarea2.pages.ConfirmationTripPage;
import com.globant.automation.tarea2.pages.SearchHotelFlightPage;
import com.globant.automation.tarea2.pages.SearchResultsPage;
import com.globant.automation.tarea2.pages.TripDetailPage;
import junit.framework.TestCase;

public class AppTest extends TestCase
{
	private MyDriver driverFactory = null;
	private WebDriver driver = null;
	private SearchHotelFlightPage searchPage = null;
	private SearchResultsPage searchResultsPage = null;
	private TripDetailPage tripDetailPage = null;
	private ConfirmationTripPage confirmationTripPage = null;
	private String URL = "https://www.travelocity.com/";
	
	public AppTest(){
		driverFactory = new MyDriver("Firefox");
		driver = driverFactory.getDriver();
		driver.get(URL);
	}
	
	/*
	 * Ejercicio 1
	 * 
	 * * */
		
	@Test
	public void exercise1() {
		searchPage = new SearchHotelFlightPage(driver);
		searchPage.clickFlightsTabButton();
		searchPage.searchFlight("LAS", "LAX", getCurrentDay().toString(), 1);
		
		searchResultsPage = new SearchResultsPage(driver);
		Assert.assertTrue(searchResultsPage.checkSearchResults());
		searchResultsPage.orderSearchResults("Duration (Shortest)");
		Assert.assertTrue(searchResultsPage.checkResultsOrderedList());
		searchResultsPage.selectFlight(0);
		searchResultsPage.orderSearchResults("Duration (Shortest)");
		searchResultsPage.selectFlight(2);
		searchResultsPage.clickNoThanks();
		
		tripDetailPage = new TripDetailPage(driver);
		Assert.assertTrue(tripDetailPage.checkTripDetails());
		tripDetailPage.clickBookButton();
		
		confirmationTripPage = new ConfirmationTripPage(driver);
		Assert.assertTrue(confirmationTripPage.checkConfirmationPage());
	}
	
	private Integer getCurrentDay(){
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	@AfterSuite
	public void cerrarSuite(){
		if(driver!=null)
			driver.close();
	}
}