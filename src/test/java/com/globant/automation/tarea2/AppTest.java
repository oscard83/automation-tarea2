package com.globant.automation.tarea2;


import java.util.Calendar;
import java.util.GregorianCalendar;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.globant.automation.tarea2.driver.MyDriver;
import com.globant.automation.tarea2.pages.PackagePreviewPage;
import com.globant.automation.tarea2.pages.ConfirmationFlightPage;
import com.globant.automation.tarea2.pages.ConfirmationPackagePage;
import com.globant.automation.tarea2.pages.SearchHotelFlightPage;
import com.globant.automation.tarea2.pages.SearchHotelPage;
import com.globant.automation.tarea2.pages.SearchHotelResultsPage;
import com.globant.automation.tarea2.pages.SearchPackageResultsPage;
import com.globant.automation.tarea2.pages.SelectFlightPackage;
import com.globant.automation.tarea2.pages.SelectRoomPage;
import com.globant.automation.tarea2.pages.SearchFlightsResultsPage;
import com.globant.automation.tarea2.pages.TripDetailPage;
import junit.framework.TestCase;

public class AppTest extends TestCase
{
	private MyDriver driverFactory = null;
	private WebDriver driver = null;
	private SearchHotelFlightPage searchPage = null;
	private SearchFlightsResultsPage searchResultsPage = null;
	private SearchPackageResultsPage searchPackageResultsPage = null;
	private TripDetailPage tripDetailPage = null;
	private ConfirmationFlightPage confirmationFlightPage = null;
	private SelectRoomPage selectRoomPage = null;
	private SelectFlightPackage selectFlightPackage = null;
	private PackagePreviewPage packagePreviewPage = null;
	private ConfirmationPackagePage confirmationPackagePage = null;
	private SearchHotelPage searchHotelPage = null;
	private SearchHotelResultsPage searchHotelResultsPage = null;
	private String URL = "https://www.travelocity.com/";
	
	public AppTest(){
		driverFactory = new MyDriver("Firefox");
		driver = driverFactory.getDriver();
	}
	
	/*
	 * Ejercicio 1
	 * 
	 * * */
		
	//@Test
	public void exercise1() {
		driver.get(URL);
		searchPage = new SearchHotelFlightPage(driver);
		searchPage.clickFlightsTabButton();
		searchPage.searchFlight("LAS", "LAX", getCurrentDay().toString(), 1);
		
		searchResultsPage = new SearchFlightsResultsPage(driver);
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
		
		confirmationFlightPage = new ConfirmationFlightPage(driver);
		Assert.assertTrue(confirmationFlightPage.checkConfirmationPage());
	}
	
	/*
	 * Ejercicio 2
	 * 
	 * * */
	
	//@Test
	public void exercise2() {
		driver.get(URL);
		searchPage = new SearchHotelFlightPage(driver);
		searchPage.clickFlightsAndHotelTabButton();
		searchPage.searchFlightHotel("LAS", "LAX", getCurrentDay().toString(), 1);
		
		searchPackageResultsPage = new SearchPackageResultsPage(driver);
		Assert.assertTrue(searchPackageResultsPage.checkSearchResults());
		searchPackageResultsPage.orderByPrice();
		Assert.assertTrue(searchPackageResultsPage.checkResultsOrderedList());
		searchPackageResultsPage.selectPackageByStars(2);
		
		selectRoomPage = new SelectRoomPage(driver);
		selectRoomPage.selectRoom(0);
		
		selectFlightPackage = new SelectFlightPackage(driver);
		selectFlightPackage.selectFlight(0);
		selectFlightPackage.selectFlight(2);
		
		packagePreviewPage = new PackagePreviewPage(driver);
		packagePreviewPage.selectCar();
		Assert.assertTrue(packagePreviewPage.checkReviewPage());
		packagePreviewPage.continueBooking();
		
		confirmationPackagePage = new ConfirmationPackagePage(driver);
		Assert.assertTrue(confirmationPackagePage.checkConfirmationPage());
	}
	
	/*
	 * Ejercicio 3
	 * 
	 * * */
	
	//@Test
	public void exercise3() {
		driver.get(URL);
		searchHotelPage = new SearchHotelPage(driver);
		searchHotelPage.clickHotelTabButton();
		searchHotelPage.searchFlight("montevideo, uruguay", getCurrentDay().toString(), 1);
		
		searchHotelResultsPage = new SearchHotelResultsPage(driver);
		Assert.assertTrue(searchHotelResultsPage.checkSponsoredHotel());
		Assert.assertTrue(searchHotelResultsPage.checkDiscountHotel());
	}
	
	@Test
	public void exercise4() {
		driver.get(URL);
		searchPage = new SearchHotelFlightPage(driver);
		searchPage.clickFlightsAndHotelTabButton();
		searchPage.searchHotelPartialStay("LAS", "LAX", getCurrentDay().toString(), 1);
		Assert.assertTrue(searchPage.checkPartialStayErrorMessage());
	}
	
	private Integer getCurrentDay(){
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	//@AfterSuite
	public void cerrarSuite(){
		if(driver!=null)
			driver.close();
	}
}