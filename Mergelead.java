package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mergelead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Application : http://leaftaps.com/opentaps/control/main

			
			6. Click Leads link
			7. Click Merge leads
			8. Click on Icon near From Lead
			9. Move to new window*/
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	 // Wait object
		
		//ChromeOptions optns = new ChromeOptions();
		//optns.addArguments("--disable-popup-blocking");	
		
		// 2. Enter the username, pwd, login
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		// 	6. Click Leads link
		driver.findElement(By.linkText("Leads")).click();
		
		// 7. Click Merge leads
		driver.findElement(By.linkText("Merge Leads")).click();
		
		// 8. Click on Icon near From Lead
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		
		// 9. Move to new window
		
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("Windows Count " +allWindows.size());
		List<String> lstWindows = new ArrayList<String>(allWindows);
		driver.switchTo().window(lstWindows.get(1));
			
		System.out.println(driver.getTitle());
		
	}

}
