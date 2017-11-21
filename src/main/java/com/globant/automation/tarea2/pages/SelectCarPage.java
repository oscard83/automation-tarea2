package com.globant.automation.tarea2.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectCarPage extends BasePage{

	public SelectCarPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "div.cf.nobullet.flex-theme-light.flex-listing.flex-collapse") //*[@id="ember780"]
	private WebElement listOfResults;

	@FindBy(className = "section-header-main")
	private WebElement headerPage;
	
	public void selectCar(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("section-header-main")));
		
		List<WebElement> carsCollection = listOfResults.findElements(By.cssSelector("div.flex-card.flex-tile.offer-card"));
		
		if(carsCollection.size()>0){
			WebElement car = carsCollection.get(0);
			WebElement selectButton = car.findElement(By.className("btn-action"));
			wait.until(ExpectedConditions.elementToBeClickable(selectButton));
			selectButton.click();//*[@id="ember770"]
		}
	}
}
