package com.globant.automation.tarea2.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectRoomPage extends BasePage{

	public SelectRoomPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "rooms-and-rates")
	private WebElement listOfRooms;
	
	public void selectRoom(int index){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("availability-header")));
		
		List<WebElement> roomsCollection = listOfRooms.findElements(By.className("book-button"));
		WebElement selectedRoom = roomsCollection.get(index);
		selectedRoom.click();
	}
}
