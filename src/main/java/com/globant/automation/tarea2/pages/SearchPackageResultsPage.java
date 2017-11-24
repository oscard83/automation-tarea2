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

/*
 * Pagina que mapea los resultados de la busqueda de paquetes
 * */

public class SearchPackageResultsPage extends BasePage{

	@FindBy(id = "resultsContainer")
	private WebElement listOfResults;
	
	@FindBy(xpath = "//*[@id='sortContainer']/div/div/div[2]/div/fieldset/ul/li[2]/button")
	private WebElement sortByPriceButton;
	
	String loadingMessage = "interstitial-message";
	
	public SearchPackageResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean checkSearchResults(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loadingMessage)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hotelResultTitle']/h1")));
		
		List<WebElement> packagesCollection = listOfResults.findElements(By.className("flex-content"));
		
		if(packagesCollection.size()>0){
		
			for(WebElement element:packagesCollection){			
				WebElement hotelTitle = element.findElement(By.className("hotelTitle"));			
				List<WebElement> qtyStarsList = element.findElements(By.cssSelector("li.starRating"));
				WebElement price = element.findElement(By.className("actualPrice"));
				WebElement qtyStars = null;
				
				if(qtyStarsList.size() > 0){
					qtyStars = qtyStarsList.get(0);
					qtyStars = qtyStars.findElement(By.className("visuallyhidden"));
				}else{
					qtyStars = element.findElement(By.cssSelector("li.hotelBadge"));
				}

				if(hotelTitle.getText().length()>0 && qtyStars.getText().length()>0 && price.getText().length()>0){
					checkResult = true;
				}else{
					checkResult = false;
					break;
				}
			}			
		}else
			checkResult = false;
		
		return checkResult;
	}
	
	public void orderByPrice(){
		wait.until(ExpectedConditions.elementToBeClickable(sortByPriceButton));
		sortByPriceButton.click();
	}
	
	public boolean checkResultsOrderedList(){
		boolean checkResult = false;
		wait.until(ExpectedConditions.attributeToBe(listOfResults, "aria-busy", "false"));
		int previousPrice = 0;
		
		List<WebElement> packagesCollection = listOfResults.findElements(By.className("flex-content"));
		
		for(WebElement element:packagesCollection){
			WebElement price = element.findElement(By.className("actualPrice"));
			String currentPriceStr = price.getText().trim();
			currentPriceStr = currentPriceStr.replace(",", "");
			currentPriceStr = currentPriceStr.replace("$", "");
			int currentPrice = Integer.parseInt(currentPriceStr);
			
			if(previousPrice <= currentPrice){
				checkResult = true;
				previousPrice = currentPrice;
			}
			else{
				checkResult = false;
				break;
			}
		}
		return checkResult;
	}
	
	public void selectPackageByStars(int stars){
		List<WebElement> packagesCollection = listOfResults.findElements(By.className("flex-link-wrap"));
		WebElement packageSelectLink = null;
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		
		for(WebElement element:packagesCollection){			
			List<WebElement> qtyStarsList = element.findElements(By.cssSelector("li.starRating"));
			WebElement qtyStars = null;
			
			if(qtyStarsList.size() > 0){
				qtyStars = qtyStarsList.get(0);
				qtyStars = qtyStars.findElement(By.className("visuallyhidden"));
				
				if(Integer.parseInt(qtyStars.getText().trim().substring(0, 1)) >= stars){
					packageSelectLink = element.findElement(By.className("flex-link"));
					js.executeScript("arguments[0].scrollIntoView(true);",packageSelectLink);
					wait.until(ExpectedConditions.elementToBeClickable(packageSelectLink));
					packageSelectLink.click();
					switchToPopUp();
					break;
				}
			}			
		}		
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