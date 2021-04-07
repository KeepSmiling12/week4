package week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LrnSelectable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get(" http://leafground.com/pages/Dropdown.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		
		// //option[text()='Selenium']//option[text()='Select your programs']/following-sibling::option
		WebElement e = driver.findElement(By.xpath("//option[text()='Select your programs']/following-sibling::option"));
		WebElement e1 = driver.findElement(By.xpath("//div[@id='contentblock']/section[1]/div[6]/select[1]/option[5]"));
		
		
		Actions actn  = new Actions(driver);
		
		actn.keyDown(Keys.CONTROL).click(e).click(e1).perform();
		
		
	}

}
