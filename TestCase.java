package week4.day2;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get(" https://www.ajio.com/shop/sale ");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	
		
		WebElement srchBags = driver.findElement(By.tagName("input"));
		
		//2) Enter Bags in the Search field and Select Women Handbags 
		srchBags.sendKeys("Bags");
		srchBags.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("(//label[contains(@class,'facet-linkname facet-linkname-genderfilter')])[3]")).click();
		driver.findElement(By.xpath("//label[contains(@class,'facet-linkname facet-linkname-l1l3nestedcategory')]")).click();
		driver.findElement(By.className("five-grid")).click();
		
		//3) Click on five grid and Select SORT BY as "What's New"
		WebElement sortByRele = driver.findElement(By.tagName("select")); 
		new Select(sortByRele).selectByVisibleText("What's New"); 
		
		//4) Click Price on the side menu and Enter Price Range Min as 2000 and Max as 5000
		driver.findElement(By.xpath("(//span[@class='facet-left-pane-label'])[3]")).click();
		driver.findElement(By.id("minPrice")).sendKeys("3000");
		driver.findElement(By.id("maxPrice")).sendKeys("5000");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		
		//5) Click on the first product driver.findElement(By.xpath("//div[@class='contentHolder']//div")).click();
		driver.findElement(By.xpath("//img[contains(@class,'rilrtl-lazy-img ')]")).click();
		Thread.sleep(2000);
		Set<String>allWindws =  driver.getWindowHandles();
		System.out.println("Windows Count " +allWindws.size());
		
		//6) Get the actual price, coupon code and discount price
		List<String> lstWindw =  new ArrayList<String>(allWindws);
		String secWindow = lstWindw.get(1);
		driver.switchTo().window(secWindow);
		//String price = driver.findElement(By.xpath("//div[text()='Rs. 3,779']")).getText();
		String price = driver.findElement(By.xpath("//div[@class='prod-price-section ']//div[1]")).getText();
		//String price = driver.findElement(By.xpath("//div[class='prod-sp']")).getText();
		System.out.println("Actual Price:  "+price);
		
		//6) Get the actual price, coupon code and discount price
		String useCodeSpecial = driver.findElement(By.xpath("//div[@class='promo-title-blck']//div[1]")).getText();
		String[] code = useCodeSpecial.split("Use Code");
		String cuCode  = code[code.length-1];
		System.out.println(cuCode);
		
		String disPrice = driver.findElement(By.xpath("//div[@class = 'promo-desc-block']/div/span")).getText();
		String disPrc = disPrice.replaceAll("[^\\d]", "");
		Integer intDisPrice = Integer.parseInt(disPrc);
		System.out.println("Discount Price displayed:  "+intDisPrice);
		
		// enter postal code    //div[@id = 'edd']//div/span[2]
		 driver.findElement(By.xpath("//div[@id = 'edd']//div/span[2]")).click();
		 driver.findElement(By.name("pincode")).sendKeys("560043");
		 driver.findElement(By.xpath("//button[text() = 'CONFIRM PINCODE']")).click();
	
		
		// Expected delivery date
		String expectedDate = driver.findElement(By.xpath("//li[text()='Expected Delivery: ']")).getText();
		System.out.println(" Expected Delivery Date:  "+expectedDate);
		
		
		//8) Click on Other Informations under Product Details and Print the Customer Care details
		// //section[@class= 'prod-desc']/h2/ul/div
		driver.findElement(By.xpath("//section[@class= 'prod-desc']/h2/ul/div")).click();
		
		// customer care details
		WebElement customerCareDetls = driver.findElement(By.xpath("//div[text() = 'Customer Care Address']/following::div[2]"));
		String c = customerCareDetls.getText();
		System.out.println("Customer Care Details: "+c);
		
		//9) Click on ADD TO BAG and then GO TO BAG //span[text() = 'ADD TO BAG']
		driver.findElement(By.xpath("//span[text() = 'ADD TO BAG']")).click();
		
		//10) Check the Order Total  //span[text() = 'GO TO BAG']
		driver.findElement(By.xpath("//span[text() = 'GO TO BAG']")).click();
		
		
		// ****************************************************************************************
		// Order Total before apply coupon
		String orderTotal = driver.findElement(By.xpath("//span[text() = 'Order Total']//following::span[1]")).getText();
		String ot = orderTotal.replaceAll("[^\\d]", "");
		ot = ot.substring(0, ot.length()-2);
		//Double OrderPrice = Double.parseDouble(ot);
		Integer intOrPr = Integer.parseInt(ot);	
		
		System.out.println("Order total before applying discount:  "+intOrPr);
		
		Thread.sleep(4000);
		//12) Print the warning message if the product is not eligible discount  SPECIAL
		//button[text() = 'Apply']  //div[@class= 'input-area']
		driver.findElement(By.id("couponCodeInput")).sendKeys(cuCode);
		driver.findElement(By.xpath("//button[text() = 'Apply']")).click();
		
		// get text from voucher box //p[@class= 'applied-voucher-message']
		WebElement voucherBoxText = driver.findElement(By.xpath("//p[@class= 'applied-voucher-message']"));
		String voucherTxt = voucherBoxText.getText();
		System.out.println("Customer Care Details: "+voucherTxt);
		
			//12(a) Verify the bill amount is matching with the discount price or not 
		String rs = driver.findElement(By.xpath("//span[@class='price-value bold-font']")).getText().replaceAll("[^\\d]", "");
		rs = rs.substring(0, rs.length()-2);		
		Integer finalPrice = Integer.parseInt(rs);		
		System.out.println("Final Price:  "+finalPrice);
		
		
		
		if (intDisPrice == finalPrice ) {
			System.out.println(" Correct Discount Applied");
		} else {
			System.out.println(" Bill total does not match discount price");
		}
		
		//13) Click on Delete and Delete the item from Bag //div[@class= 'delete-btn']
		driver.findElement(By.xpath("//div[@class= 'delete-btn']")).click();
		//  confirm by click on delete //div[@class= 'card-delete-button']/div[@class= 'delete-btn']
		driver.findElement(By.xpath("//div[@class= 'card-delete-button']/div[@class= 'delete-btn']")).click();
				
		//14) Close all the browsers
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
