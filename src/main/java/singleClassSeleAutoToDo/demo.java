package singleClassSeleAutoToDo;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class demo {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		
		driver.get("https://springexplorers.vercel.app/");
		
		
		WebElement date= driver.findElement(By.id("dateInput"));
		date.click();
		date.sendKeys("09/12/2003");
		
		driver.findElement(By.id("startTimeInput")).click();
		driver.findElement(By.id("startTimeInput")).sendKeys("01:30 2");
	}
}
