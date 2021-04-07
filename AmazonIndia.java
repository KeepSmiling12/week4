package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonIndia {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	
		Actions action  = new Actions(driver);
		
	//2. Type "one plus 7 pro mobiles" in Search Box and Enter //div[@class='nav-search-field ']/input		
		driver.findElement(By.xpath("//div[@class='nav-search-field ']/input")).sendKeys("one plus 7 pro mobiles", Keys.ENTER);
		
	//3. Print the price of the first resulting mobile
		String firstP = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		
	//4. Click on the Mobile (First resulting) image //img[@data-image-source-density='1'][1]
		driver.findElement(By.xpath("//img[@data-image-index='2']")).click();
		
	//5. Switch to the new window
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("Windows Count " +allWindows.size());
		List<String> lstWindows = new ArrayList<String>(allWindows);		
		driver.switchTo().window(lstWindows.get(1));
		
	//6. Print the number of customer ratings acrCustomerReviewText
		String rating = driver.findElement(By.id("acrCustomerReviewText")).getText();
		System.out.println("rating of Iphone:  "+rating);
		
	//7. Click 'Add to Cart'	add-to-cart-button
		driver.findElement(By.id("add-to-cart-button")).click();
		
	//8. Confirm "Added to Cart" text message appeared Added to Cart
		String addedToCart = driver.findElement(By.xpath("(//h4[text()='Added to Cart'])[2]")).getText();
		
	//9. Click on Proceed to checkout //form[@id = 'attach-view-cart-button-form']/following-sibling::span//input
		driver.findElement(By.xpath("//form[@id = 'attach-view-cart-button-form']/following-sibling::span//input")).click();
		
	//10. Confirm the title is "Amazon Sign In" Sign-In  //h1[contains(text(),'Sign-In')]
		System.out.println("Windows Count " +allWindows.size());
		driver.switchTo().window(lstWindows.get(1));
		String signIn = driver.findElement(By.xpath("//h1[contains(text(),'Sign-In')]")).getText();
		System.out.println("Found the sign-in text: "+signIn);
		
	//11. Click Continue (without entering mobile number/email) continue
		driver.findElement(By.id("continue")).click();
		
		Thread.sleep(4000);
	//12. Verify the error message: Enter your email or mobile phone number //div[@class ='a-alert-content']
		String errorMessage = driver.findElement(By.xpath("(//div[@class='a-alert-content'])[2]")).getText();
		System.out.println(" Error Message: "+errorMessage);
		
	//13. Close both browsers*/
		
		driver.quit();
		
	}

}
