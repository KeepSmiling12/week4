package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertWindowHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 * 1. Load https://www.irctc.co.in/
		2. Click on OK button in the dialog  // Alert
		3. Click on FLIGHTS link             // New window
		4. Go to the Flights tab
		5. Print the customer care email id
		6. Close the First tab(Train ticket booking) alone
		 */
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get(" https://www.irctc.co.in/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.manage().window().maximize();		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	 // Wait object		
		ChromeOptions optns = new ChromeOptions();
		optns.addArguments("--disable-popup-blocking");		
				
		// 2. Click on OK button in the dialog  // Alert
		WebElement visible = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		wait.until(ExpectedConditions.visibilityOf(visible)) ;			// Condition to wait
		visible.click();
		
		//3. Click on FLIGHTS link             // New window
		driver.findElement(By.xpath("//li[@id='flight']//span[1]")).click();
		
		// find windows open and move to sec tab
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("Windows Count " +allWindows.size());
		List<String> lstWindows = new ArrayList<String>(allWindows);
		String firstWindow = 	lstWindows.get(0);	
		//4. Go to the Flights tab
		driver.switchTo().window(lstWindows.get(1));
		 
		Thread.sleep(3000);
		// 5. Print the customer care email id
		String emailContact = driver.findElement(By.xpath("//a[@href='mailto:flights@irctc.co.in']")).getText();
		System.out.println(emailContact);
		
		// 6. Close the First tab(Train ticket booking) alone
		for (String string : lstWindows) {
			if(string ==firstWindow ) {
				driver.switchTo().window(string).close();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		driver.findElement(By.xpath("//button[@onclick = 'openWindows()']")).click();
		
		Set<String>allWindws =  driver.getWindowHandles();
		System.out.println("Windows Count " +allWindws.size());
		
		List<String> lstWindw =  new ArrayList<String>(allWindws);
		String secWindow = lstWindw.get(2);
		driver.switchTo().window(secWindow);
		System.out.println("sec Window: " +driver.getWindowHandle());
		System.out.println(" sec Window title:  "+driver.getTitle());
	}

}
