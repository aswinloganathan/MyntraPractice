package Day1;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class Myntra {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Move to element WOMEN and click JACKETS & COATS
		Actions action = new Actions(driver);

		WebElement women = driver.findElement(By.xpath("//a[text()='Women' and @class='desktop-main']"));
		action.moveToElement(women).perform();
		Thread.sleep(5000);
		
		WebElement jktCoat = driver.findElement(By.xpath("//a[text()='Jackets & Coats']"));
		action.moveToElement(jktCoat).click().perform();
		
		
		String count = driver.findElement(By.className("title-count")).getText();
		String rplcText = count.replaceAll("\\D", "");
		int totalCount = Integer.parseInt(rplcText);
		System.out.println("Total product count is: "+totalCount);
		
		//No of Jackets
		String jackets = driver.findElement(By.xpath("//input[@value='Jackets']//parent::label/span")).getText();
		String rplJackets = jackets.replaceAll("\\D", "");
		int jktCount = Integer.parseInt(rplJackets);
		
		//No of coats
		String coats = driver.findElement(By.xpath("//input[@value='Coats']//parent::label/span")).getText();
		String rplCoats = coats.replaceAll("\\D", "");
		int coatCount = Integer.parseInt(rplCoats);		
		
		//Compare TOTAL COUNT
		int totalCount2 = jktCount+coatCount;
		
		if (totalCount==totalCount2) {
			System.out.println("Both the values are same");
		} else {
			System.out.println("Values differ from each other");
		}
		
		//Click coat and more
		driver.findElement(By.xpath("//input[@value='Coats']//parent::label")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("brand-more")).click();
		Thread.sleep(3000);
		
		//Search MANGO Brand and close menu
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Mango",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-list']/li/label")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-indices']/following-sibling::span")).click();
		
		
		//Find number of MANGO Product		
		List<WebElement> brandList = driver.findElements(By.tagName("h3"));
		for (WebElement eleBrand : brandList) {
			String name = eleBrand.getText();
			if (name.equalsIgnoreCase("MANGO")) {
				System.out.println("true");
			}
		}
		
		//Mouse Hover and sort product based on Discount
		WebElement sortBy = driver.findElement(By.className("sort-sortBy"));
		action.moveToElement(sortBy).perform();
		
		WebElement discount = driver.findElement(By.xpath("//label[text()='Better Discount']"));
		action.moveToElement(discount).click().perform();
		Thread.sleep(3000);
		
		
		//to get the Price and convert String into INTEGER
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
		String dressPrice = price.get(0).getText();
		String rplcPrice = dressPrice.replaceAll("\\D", "");
		int finalPrice = Integer.parseInt(rplcPrice);
		System.out.println("Price of the first product is:"+finalPrice);
		
		//Mouse Hover on Product to enable Wishlist
		WebElement product = driver.findElement(By.className("product-base"));
		action.moveToElement(product).perform();
		Thread.sleep(3000);
		
		WebElement sizeOfPrd = driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/a/div/h4/span[4]"));
		action.moveToElement(sizeOfPrd).perform();
		
		//Click WishList
		driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/div[3]/span")).click();
		
		System.out.println("Page redirected to"+ driver.getTitle());
		
		driver.close();
		
		
	}
}
