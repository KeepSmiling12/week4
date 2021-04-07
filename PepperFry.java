package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PepperFry {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.get(" https://www.pepperfry.com/ ");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	
		Actions action  = new Actions(driver);
		
		//2) Mouseover on Furniture and click Office Chairs under Chairs
		
			WebElement furniture = driver.findElement(By.linkText("Furniture"));
			action.moveToElement(furniture).perform();
				
			driver.findElement(By.linkText("Office Chairs")).click();
		//3) click Executive Chairs
			driver.findElement(By.xpath("(//div[@class='cat-wrap-img'])[2]")).click();
		
		//4) Change the minimum Height to 50 in under Dimensions  
			//WebElement defaultHeight = driver.findElement(By.xpath("//span[@class = '(//span[@class = 'ui-slider-handle ui-corner-all ui-state-default'])[1]']"));
			//WebElement minHeight = driver.findElement(By.xpath("//div[@style = 'left: 83.3333%; width: 16.6667%;']"));
			
			//action.dragAndDrop(defaultHeight, minHeight).perform();
			
			WebElement height1 = driver.findElement(By.xpath("//input[@class='clipFilterDimensionHeightValue']"));
			height1.clear();
			height1.sendKeys("50");
			height1.sendKeys(Keys.RETURN);
			
			Thread.sleep(2000);
			// 5) Add "Galician High Back Executive Chair In Black Colour" chair to Wishlist
			//WebElement galicianHighBack = driver.findElement(By.linkText("Galician High Back Executive Chair in Black Colour"));
			driver.findElement(By.xpath("//a[@data-productname='Galician High Back Executive Chair in Black Colour']")).click();
			Thread.sleep(2000);
			//6) Mouseover on Bedroom 
			WebElement bedroom = driver.findElement(By.linkText("Bedroom"));
			action.moveToElement(bedroom).perform();
			// Click Study tables
			driver.findElement(By.xpath("//div[@id='meta-bedroom']/div[1]/div[4]/div[2]/div[3]/a[1]")).click();

			//7) Select Spacewood as Brand
			driver.findElement(By.xpath("//label[text()='Spacewood']")).click();
			Thread.sleep(2000);
			// 9) Add "SOS Carter Study Table In Lorraine Walnut & Silver Grey Finish " to Wishlist
			driver.findElement(By.xpath("//a[@data-productname='SOS Carter Study Table in Lorraine walnut & silver grey Finish']")).click();
			
			Thread.sleep(2000);
			// 10) Verify the number of items in the Wishlist
			String wishListNum = driver.findElement(By.xpath("//span[@class='header-nav-icon']//span")).getText();
			System.out.println(wishListNum);
			Thread.sleep(2000);
			//11) Navigate to Wishlist
			driver.findElement(By.xpath("//a[@class='wishlist_bar']//span")).click();
						
			//12) Move Table only to Cart from Wishlist			
			driver.findElement(By.xpath("//a[@data-tooltip='Add to Cart']")).click();
			
			Thread.sleep(3000);
			//a[class='popup-close'] //div[@id = 'reg_login_box']/div/a
			driver.findElement(By.xpath("//div[@id = 'reg_login_box']/div/a")).click();
			
			// _we_wk_data_store
			driver.switchTo().frame("notification-frame-~251447426");

			//span[@class = 'wewidgeticon we_close icon-large']  //span[@class = 'wewidgeticon we_close icon-large'] //div[@class =' prime']//div/div/div/span
			driver.findElement(By.xpath("//div[@class ='close']/span")).click();
			//if (element.isDisplayed() && element.isEnabled()) {
			//    	element.click();
			//}
			
			driver.switchTo().defaultContent();
		
			//13) Click Proceed to Pay Securely //div[@id='minicart_footer']/div/a
			driver.findElement(By.xpath("//div[@id='minicart_footer']/div/a")).click();
			
			//14) Enter Pincode as 600028 in Delivery & Assembly Details and click Go
			driver.findElement(By.id("pin_code")).sendKeys("600028");
			driver.findElement(By.id("pin_check")).click();
			
			//14) Click Place Order
			driver.findElement(By.xpath("//a[@href='https://www.pepperfry.com/checkout/onepage/']")).click();
			
			//15) Capture a screenshot by Clicking on Order Summary
			driver.findElement(By.xpath("//span[text()='ORDER SUMMARY']")).click();
			
			//15) Capture a screenshot by Clicking on Order Summary
			driver.findElement(By.xpath("//span[text()='ORDER SUMMARY']")).click();
			
			// take ScreenShot getScreenshotAs() and store the information in a variable
			File memory = driver.getScreenshotAs(OutputType.FILE);
			// to copy the screenShot to your project use FileUtils.copyFile() and specify path
			try {
				FileUtils.copyFile(memory, new File("./snaps/snap1.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			//16) Close the browser 
			
			//driver.quit();
	}

}
