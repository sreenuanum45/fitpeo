package utilities.WebsiteUtlity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DataPicker {
public void dateSelector( WebElement dp,RemoteWebDriver driver) throws Throwable {
	int ExceptedYear=2023;
	String Exceptedmonth="august";
	int day=5;

	driver.executeScript("arguments[0].scrollintoview", dp);
	dp.click();
	while(true) {
		String temp=driver.findElement(By.xpath("")).getText();
		int currentyear=Integer.parseInt(temp);
		if(ExceptedYear>currentyear) {
			driver.findElement(By.xpath("")).click();
			Thread.sleep(1000);
		}
		else if(ExceptedYear<currentyear) {
			driver.findElement(By.xpath("")).click();
			Thread.sleep(1000);
		}
		else {
			break;
		}
	}
	while(true) {
		String currentmonth=driver.findElement(By.xpath("")).getText();
		if(currentmonth.equalsIgnoreCase("january")) {
			break;
		}
		else {
			driver.findElement(By.xpath("")).click();
		}
	}
	while(true) {
		String currentmonth=driver.findElement(By.xpath("")).getText();
		if(currentmonth.equalsIgnoreCase(Exceptedmonth)) {
			break;
		}
		else{
			driver.findElement(By.xpath("")).click();
			Thread.sleep(1000);
		}
	}
	driver.findElement(By.linkText("")).click();
	
}
	
	
}
