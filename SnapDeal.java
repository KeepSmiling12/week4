package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	
		Actions action  = new Actions(driver);
		
	// 2. Go to Mens Fashion
		action.moveToElement(driver.findElement(By.xpath("//div[@id='leftNavMenuRevamp']/div[1]/div[2]/ul[1]/li[7]/a[1]/span[1]"))).perform();
		
	// 3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
	// 4. Get the count of the sports shoes
		String sportsShoesCount = driver.findElement(By.className("child-cat-count")).getText();
		String s = sportsShoesCount.replaceAll("[^\\d]", "");
		int sprtShoesCnt  = Integer.parseInt(s);
		System.out.println("Total Sports Shoes available: "+sprtShoesCnt);
		
	// 5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
	// 6. Sort by Low to High		
		driver.findElement(By.xpath("//div[text()[normalize-space()='Popularity']]")).click();
		// //div[@class = 'sorting-sec animBounce']//ul/li[2]
		driver.findElement(By.xpath("//div[@class = 'sorting-sec animBounce']//ul/li[2]")).click();
		
		
	// 7. Check if the items displayed are sorted correctly  
		//action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		 //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='lfloat product-price']")));
		 ArrayList priceSort = new ArrayList();
		 List<WebElement> trainingShoesSort = driver.findElements(By.xpath("//span[@class='lfloat product-price']")); 		
	// Scroll Down using Actions class	    
		 System.out.println(trainingShoesSort.size());
		for (int i = 0; i < trainingShoesSort.size(); i++) {
			
			String s1 = trainingShoesSort.get(i).getText().replaceAll("[^\\d]", "");
			int x = Integer.parseInt(s1);
			priceSort.add(x);	
		}
		Collections.sort(priceSort);
		System.out.println("  S"+priceSort);
		 
		
		//Search for Puma Blue Training Shoes
		 driver.findElement(By.xpath("//input[@id ='searchWithinSearch']")).sendKeys("Puma Blue", Keys.ENTER);
		// 8. Mouse Hover on puma Blue Training shoes
		action.moveToElement(driver.findElement(By.xpath("//img[@title='Puma Blue Training Shoes']"))).perform();
		//9. click QuickView button //div[@class='clearfix row-disc']/div[1]
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div[1]")).click();
		// Cost //div[text() = 'Price'] //div[@class = 'lfloat']
		String price  = driver.findElement(By.xpath("//div[@class = 'lfloat']/div[2]/span[1]")).getText();
		System.out.println("Cost of Shoes: "+price);
		// discount
		String discount  = driver.findElement(By.xpath("//div[@class = 'lfloat']/div[2]/span[2]")).getText();
		System.out.println("Discount offered for Shoes: "+discount);
		
		//snapShot save to a file, copy file from src to destination
				File f = driver.getScreenshotAs(OutputType.FILE);
				
				FileUtils.copyFile(f, new File("./snaps/shoes.jpg"));				
		
		//close window
		driver.findElement(By.xpath("//div[@class ='close close1 marR10']/i")).click();
		
		driver.close();
		
	}

}
